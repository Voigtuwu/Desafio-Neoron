package br.com.neoron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neoron.entity.Aeroportos;
import br.com.neoron.exeception.ResourceNotFoundException;
import br.com.neoron.repository.AeroportosRepository;

@Service
public class AeroportoService {

	@Autowired
	private AeroportosRepository aeroportosRepository;

	public Aeroportos criarAeroporto(Aeroportos a) {
		return aeroportosRepository.save(a);
	}
	
	public Aeroportos atualizarAeroporto(Long id, Aeroportos a) {
	    return aeroportosRepository.findById(id)
	        .map(aeroportoExistente -> {
	            if (a.getCep() != null) {
	                aeroportoExistente.setCep(a.getCep());
	            }
	            if (a.getPais() != null) {
	                aeroportoExistente.setPais(a.getPais());
	            }
	            if (a.getCidade() != null) {
	                aeroportoExistente.setCidade(a.getCidade());
	            }
	            if (a.getEstado() != null) {
	                aeroportoExistente.setEstado(a.getEstado());
	            }
	            return aeroportosRepository.save(aeroportoExistente);
	        })
	        .orElseThrow(() -> new ResourceNotFoundException("Aeroporto com ID " + id + " não encontrado."));
	}
	
	public void deletarAeroporto(Long id) {
	    if (aeroportosRepository.existsById(id)) {
	        aeroportosRepository.deleteById(id);
	    } else {
	        throw new ResourceNotFoundException("Aeroporto com ID " + id + " não encontrado.");
	    }
	}

	public Aeroportos listarPorId(Long id) {
		return aeroportosRepository.findById(id).orElse(null);
	}
	
	public List<Aeroportos> listarTodos() {
		List<Aeroportos> aeroportos = aeroportosRepository.findAll();
		return aeroportos;
	}

}
