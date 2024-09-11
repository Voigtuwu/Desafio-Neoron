package br.com.neoron.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neoron.entity.Aeroportos;
import java.util.List;


@Repository
public interface AeroportosRepository extends JpaRepository<Aeroportos, Long> {
	Optional<Aeroportos> findById(Long id);
	List<Aeroportos> findAll();
}
