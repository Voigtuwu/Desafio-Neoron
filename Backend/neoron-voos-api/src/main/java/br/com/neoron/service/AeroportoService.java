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

	// Método para criar um novo aeroporto
	public Aeroportos criarAeroporto(Aeroportos a) {
		return aeroportosRepository.save(a); // Salva o aeroporto no banco de dados
	}

	// Método para atualizar um aeroporto existente
	public Aeroportos atualizarAeroporto(Long id, Aeroportos a) {
		return aeroportosRepository.findById(id).map(aeroportoExistente -> {
			// Atualiza apenas os campos não nulos fornecidos na requisição
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
			return aeroportosRepository.save(aeroportoExistente); // Salva as mudanças no banco de dados
		}).orElseThrow(() -> new ResourceNotFoundException("Aeroporto com ID " + id + " não encontrado.")); // Lança
																											// exceção
																											// se o ID
																											// não for
																											// encontrado
	}

	// Método para deletar um aeroporto pelo ID
	public void deletarAeroporto(Long id) {
		if (aeroportosRepository.existsById(id)) {
			aeroportosRepository.deleteById(id); // Deleta o aeroporto se ele existir
		} else {
			throw new ResourceNotFoundException("Aeroporto com ID " + id + " não encontrado."); // Lança exceção se o ID
																								// não for encontrado
		}
	}

	// Método para buscar um aeroporto pelo ID
	public Aeroportos listarPorId(Long id) {
		return aeroportosRepository.findById(id).orElse(null); // Retorna o aeroporto ou null se não encontrado
	}

	// Método para listar todos os aeroportos
	public List<Aeroportos> listarTodos() {
		return aeroportosRepository.findAll(); // Retorna todos os aeroportos cadastrados
	}
}
