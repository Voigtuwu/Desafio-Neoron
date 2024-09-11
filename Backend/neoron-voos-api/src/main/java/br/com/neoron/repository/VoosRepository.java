package br.com.neoron.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neoron.entity.Voos;



@Repository
public interface VoosRepository extends JpaRepository<Voos, UUID> {
	Optional<Voos> findById(UUID codigoVoo);
	//List<Voos> findByData(LocalDate data);
	//List<Voos> findByHour(LocalDateTime hora);
	List<Voos> findAll();
}
