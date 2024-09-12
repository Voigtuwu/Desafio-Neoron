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

        // Condição para sobreposição de horários quando a origem e destino são os mesmos
        Predicate partidaOverlapMesmoOrigemDestino = cb.and(
            cb.equal(voo.get("origem"), origem),
            cb.equal(voo.get("destino"), destino),
            cb.lessThanOrEqualTo(voo.get("horaPartida"), endTime.plusMinutes(30)),
            cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
        );

        // Condição para sobreposição de chegada quando o destino é o mesmo (origens diferentes)
        Predicate chegadaOverlapMesmoDestinoOrigemDiferente = cb.and(
        	    cb.equal(voo.get("destino"), destino), // mesmo destino
        	    cb.notEqual(voo.get("origem"), origem), // origem diferente
        	    cb.and(
        	        cb.lessThanOrEqualTo(voo.get("horaChegada"), endTime.plusMinutes(30)), // chegada deve ser antes do final do intervalo
        	        cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))  // chegada deve ser depois do início do intervalo
        	    )
        	);

        // Condição para verificar se há um conflito entre a chegada de um voo no aeroporto de origem com a partida de outro voo
        Predicate partidaConflitoChegadaOrigem = cb.and(
                cb.equal(voo.get("dataPartida"), dataPartida),
                cb.or(
                    // Se a origem do novo voo é o destino de um voo existente
                    cb.and(
                        cb.equal(voo.get("destino"), origem), // mesmo destino do voo existente
                        cb.lessThanOrEqualTo(voo.get("horaChegada"), startTime.plusMinutes(30)),
                        cb.greaterThanOrEqualTo(voo.get("horaChegada"), startTime.minusMinutes(30))
                    ),
                    // Se a origem do novo voo é igual à origem do voo existente
                    cb.and(
                        cb.equal(voo.get("origem"), origem), // mesma origem
                        cb.lessThanOrEqualTo(voo.get("horaPartida"), endTime.plusMinutes(30)),
                        cb.greaterThanOrEqualTo(voo.get("horaPartida"), startTime.minusMinutes(30))
                    )
                )
            );

        // Combinando todas as condições de conflito
        Predicate conflito = cb.or(
            partidaOverlapMesmoOrigemDestino, // conflito no mesmo origem e destino
            chegadaOverlapMesmoDestinoOrigemDiferente, // conflito em origens diferentes e mesmo destino
            partidaConflitoChegadaOrigem // sobreposição com chegada no aeroporto de origem
        );

        cq.select(cb.count(voo)).where(dataIgual, conflito);

        return entityManager.createQuery(cq).getSingleResult() > 0;
    }
}
