package br.com.neoron.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import br.com.neoron.entity.Aeroportos;
import br.com.neoron.entity.Voos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class VoosRepositoryCustomImpl implements VoosRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsConflictingFlights(Aeroportos origem, Aeroportos destino, LocalDate dataPartida, LocalDateTime startTime, LocalDateTime endTime) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Voos> voo = cq.from(Voos.class);

        // Condição para a mesma data
        Predicate dataIgual = cb.equal(voo.get("dataPartida"), dataPartida);

        // Condição para sobreposição de horários na mesma origem e destino
        Predicate partidaOverlapMesmoOrigemDestino = cb.and(
            cb.equal(voo.get("origem"), origem),
            cb.equal(voo.get("destino"), destino),
            cb.lessThanOrEqualTo(voo.get("horaPartida"), endTime.plusMinutes(30)),
            cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
        );

        // Condição para sobreposição de chegada em destinos diferentes com a mesma chegada
        Predicate chegadaOverlapMesmoDestinoOrigemDiferente = cb.and(
            cb.equal(voo.get("destino"), destino),
            cb.notEqual(voo.get("origem"), origem),
            cb.and(
                cb.lessThanOrEqualTo(voo.get("horaChegada"), endTime.plusMinutes(30)),
                cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
            )
        );

        // Condição para verificar partidas no mesmo aeroporto de destino
        Predicate partidaConflitoChegadaDestino = cb.and(
            cb.equal(voo.get("dataPartida"), dataPartida),
            cb.or(
                // Se o destino do novo voo é a origem de um voo existente
                cb.and(
                    cb.equal(voo.get("origem"), destino),
                    cb.lessThanOrEqualTo(voo.get("horaPartida"), endTime.plusMinutes(30)),
                    cb.greaterThanOrEqualTo(voo.get("horaPartida"), startTime.minusMinutes(30))
                ),
                // Se o destino do novo voo é o mesmo destino de um voo existente
                cb.and(
                    cb.equal(voo.get("destino"), destino),
                    cb.lessThanOrEqualTo(voo.get("horaChegada"), endTime.plusMinutes(30)),
                    cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
                )
            )
        );

        // Combinando todas as condições de conflito
        Predicate conflito = cb.or(
            partidaOverlapMesmoOrigemDestino,
            chegadaOverlapMesmoDestinoOrigemDiferente,
            partidaConflitoChegadaDestino
        );

        cq.select(cb.count(voo)).where(dataIgual, conflito);

        return entityManager.createQuery(cq).getSingleResult() > 0;
    }

}
