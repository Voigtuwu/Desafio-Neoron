package br.com.neoron.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.neoron.entity.Aeroportos;

public interface VoosRepositoryCustom {
    boolean existsConflictingFlights(Aeroportos origem, Aeroportos destino, LocalDate dataPartida, LocalDateTime startTime, LocalDateTime endTime);
}
