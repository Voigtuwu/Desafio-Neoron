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
public interface VoosRepository extends JpaRepository<Voos, UUID>, VoosRepositoryCustom {

	Optional<Voos> findById(UUID codigoVoo);

	List<Voos> findAll();

	List<Voos> findByOrigemAndDestinoAndDataPartida(Aeroportos origem, Aeroportos destino, LocalDate dataPartida);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :origem " + "AND v.dataPartida = :dataPartida "
			+ "AND (v.horaPartida < :endTime AND v.horaChegada > :startTime)")
	boolean existsConflictingFlightsAtOrigin(@Param("origem") Aeroportos origem,
			@Param("dataPartida") LocalDate dataPartida, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.destino = :destino " + "AND v.dataPartida = :dataPartida "
			+ "AND (v.horaPartida < :endTime AND v.horaChegada > :startTime)")
	boolean existsConflictingArrivalsAtDestination(@Param("destino") Aeroportos destino,
			@Param("dataPartida") LocalDate dataPartida, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :destino " + "AND v.dataPartida = :dataPartida "
			+ "AND (v.horaPartida < :endTime AND v.horaPartida > :startTime)")
	boolean existsConflictingDeparturesFromDestination(@Param("destino") Aeroportos destino,
			@Param("dataPartida") LocalDate dataPartida, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :origem " + "AND v.destino = :destino "
			+ "AND v.dataPartida = :dataPartida " + "AND v.id != :id "
			+ "AND (v.horaPartida < :endTime AND v.horaChegada > :startTime)")
	boolean existsConflictingFlightsExcept(@Param("id") UUID id, @Param("origem") Aeroportos origem,
			@Param("destino") Aeroportos destino, @Param("dataPartida") LocalDate dataPartida,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.destino = :destino " + "AND v.dataPartida = :dataPartida "
			+ "AND v.codigoVoo != :id " + "AND (v.horaPartida < :endTime AND v.horaChegada > :startTime)")
	boolean existsConflictingArrivalFlightsExcept(@Param("id") UUID id, @Param("destino") Aeroportos destino,
			@Param("dataPartida") LocalDate dataPartida, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :origem " + "AND v.destino = :destino "
			+ "AND v.dataPartida = :dataPartida " + "AND v.id != :id")
	boolean existsSameOriginAndDestinationOnSameDayExcluding(@Param("id") UUID id, @Param("origem") Aeroportos origem,
			@Param("destino") Aeroportos destino, @Param("dataPartida") LocalDate dataPartida);

	@Query("SELECT COUNT(v) > 0 FROM Voos v WHERE v.origem = :origem " + "AND v.destino = :destino "
			+ "AND v.dataPartida = :dataPartida")
	boolean existsSameOriginAndDestinationOnSameDay(@Param("origem") Aeroportos origem,
			@Param("destino") Aeroportos destino, @Param("dataPartida") LocalDate dataPartida);
}
