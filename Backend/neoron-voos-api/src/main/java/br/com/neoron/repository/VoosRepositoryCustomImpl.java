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

        // Condições para sobreposição de horários com mesma origem e destino
        Predicate partidaOverlapMesmoOrigemDestino = cb.and(
            cb.lessThanOrEqualTo(voo.get("horaPartida"), endTime.plusMinutes(30)),
            cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
        );

        // Condições para sobreposição de horários com destinos iguais e origens diferentes (verifica só a hora de chegada)
        Predicate chegadaOverlapDestinoDiferente = cb.and(
            cb.lessThanOrEqualTo(voo.get("horaChegada"), endTime.plusMinutes(30)),
            cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
        );

        // Condição para voos com mesma origem e destino
        Predicate conflitoMesmoOrigemDestino = cb.and(
            cb.equal(voo.get("origem"), origem),
            cb.equal(voo.get("destino"), destino),
            partidaOverlapMesmoOrigemDestino
        );

        // Condição para voos com o mesmo destino, mas origem diferente (apenas verificar hora de chegada)
        Predicate conflitoDestinoIgualOrigemDiferente = cb.and(
            cb.equal(voo.get("destino"), destino),
            cb.notEqual(voo.get("origem"), origem),
            chegadaOverlapDestinoDiferente
        );

        // Combinar as condições de conflito
        Predicate conflito = cb.or(conflitoMesmoOrigemDestino, conflitoDestinoIgualOrigemDiferente);

        cq.select(cb.count(voo))
            .where(dataIgual, conflito);

        return entityManager.createQuery(cq).getSingleResult() > 0;
    }


}
