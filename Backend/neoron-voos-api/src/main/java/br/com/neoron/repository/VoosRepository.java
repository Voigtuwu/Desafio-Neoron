package br.com.neoron.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.neoron.entity.Aeroportos;
import br.com.neoron.entity.Voos;

@Repository
public interface VoosRepository extends JpaRepository<Voos, UUID> {
	Optional<Voos> findById(UUID codigoVoo);

	List<Voos> findAll();

	List<Voos> findByOrigemAndDestinoAndDataPartida(Aeroportos origem, Aeroportos destino, LocalDate dataPartida);

	boolean existsByDestinoAndDataPartida(Aeroportos destino, LocalDate dataPartida);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :origem "
			+ "AND ((v.horaPartida < :endTime AND v.horaChegada > :startTime))")
	boolean existsConflictingFlights(@Param("origem") Aeroportos origem, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);
}
