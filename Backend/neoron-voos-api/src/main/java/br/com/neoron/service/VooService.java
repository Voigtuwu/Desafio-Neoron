package br.com.neoron.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neoron.dto.VooResponseDTO;
import br.com.neoron.entity.Aeroportos;
import br.com.neoron.entity.Voos;
import br.com.neoron.exeception.CustomException;
import br.com.neoron.exeception.ResourceNotFoundException;
import br.com.neoron.repository.AeroportosRepository;
import br.com.neoron.repository.VoosRepository;

@Service
public class VooService {

	@Autowired
	VoosRepository voosRepository;

	@Autowired
	AeroportosRepository aeroportosRepository;

	@Transactional
	public VooResponseDTO criarVoo(Voos voo) {
		Aeroportos origem = aeroportosRepository.findById(voo.getOrigem().getId())
				.orElseThrow(() -> new CustomException("Origem não encontrada"));
		Aeroportos destino = aeroportosRepository.findById(voo.getDestino().getId())
				.orElseThrow(() -> new CustomException("Destino não encontrado"));

		voo.setOrigem(origem);
		voo.setDestino(destino);

		// Verificar se já existe um voo com a mesma origem e destino na mesma data
		if (voosRepository.existsSameOriginAndDestinationOnSameDay(origem, destino, voo.getDataPartida())) {
			throw new CustomException(
					"Já existe um voo cadastrado para a origem " + origem.getNome() + " e destino " + destino.getNome()
							+ " na data " + voo.getDataPartida().toString() + ". Por favor, escolha outra data.");
		}

		// Verificar se há um conflito de horários
		LocalDateTime startTime = voo.getHoraPartida();
		LocalDateTime endTime = voo.getHoraChegada();

		if (voosRepository.existsConflictingFlights(origem, destino, voo.getDataPartida(), startTime, endTime)) {
			throw new CustomException("O voo a partir do aeroporto " + origem.getNome() + " para " + destino.getNome()
					+ " entre " + startTime.toString() + " e " + endTime.toString()
					+ " conflita com outro voo existente. " + "Por favor, ajuste os horários.");
		}

		Voos savedVoo = voosRepository.save(voo);
		return convertToResponseDTO(savedVoo);
	}
	
	@Transactional
	public VooResponseDTO atualizarVoo(UUID codigoVoo, Voos novoVoo) {
	    Voos vooExistente = voosRepository.findById(codigoVoo)
	            .orElseThrow(() -> new CustomException("Voo não encontrado"));

	    Aeroportos novaOrigem = novoVoo.getOrigem() != null ?
	            aeroportosRepository.findById(novoVoo.getOrigem().getId())
	                .orElseThrow(() -> new CustomException("Origem não encontrada")) : vooExistente.getOrigem();
	    Aeroportos novoDestino = novoVoo.getDestino() != null ?
	            aeroportosRepository.findById(novoVoo.getDestino().getId())
	                .orElseThrow(() -> new CustomException("Destino não encontrado")) : vooExistente.getDestino();

	    // Atualizar os campos do voo existente
	    if (novoVoo.getOrigem() != null) vooExistente.setOrigem(novaOrigem);
	    if (novoVoo.getDestino() != null) vooExistente.setDestino(novoDestino);
	    if (novoVoo.getDataPartida() != null) vooExistente.setDataPartida(novoVoo.getDataPartida());
	    if (novoVoo.getDataChegada() != null) vooExistente.setDataChegada(novoVoo.getDataChegada());
	    if (novoVoo.getHoraPartida() != null) vooExistente.setHoraPartida(novoVoo.getHoraPartida());
	    if (novoVoo.getHoraChegada() != null) vooExistente.setHoraChegada(novoVoo.getHoraChegada());

	    // Verificar se há conflito de partidas (origem)
	    LocalDateTime startTime = vooExistente.getHoraPartida();
	    LocalDateTime endTime = vooExistente.getHoraChegada();

	    if (voosRepository.existsConflictingFlightsExcept(
	            vooExistente.getCodigoVoo(), 
	            vooExistente.getOrigem(), 
	            vooExistente.getDestino(),
	            vooExistente.getDataPartida(),
	            startTime.minus(30, ChronoUnit.MINUTES), 
	            endTime.plus(30, ChronoUnit.MINUTES))) {
	        throw new CustomException("Há um voo existente que se sobrepõe ao horário da partida.");
	    }

	    // Verificar se há conflito de chegadas (destino)
	    if (voosRepository.existsConflictingArrivalFlightsExcept(
	            vooExistente.getCodigoVoo(), 
	            vooExistente.getDestino(), 
	            vooExistente.getDataPartida(),
	            startTime.minus(30, ChronoUnit.MINUTES), 
	            endTime.plus(30, ChronoUnit.MINUTES))) {
	        throw new CustomException("Há um voo existente que se sobrepõe ao horário da chegada.");
	    }

	    Voos updatedVoo = voosRepository.save(vooExistente);
	    return convertToResponseDTO(updatedVoo);
	}

	public void deletarVoo(UUID codigoVoo) {
		if (voosRepository.existsById(codigoVoo)) {
			voosRepository.deleteById(codigoVoo);
		} else {
			throw new ResourceNotFoundException("Voo com código " + codigoVoo + " não encontrado");
		}
	}

	public List<VooResponseDTO> listarTodos() {
		List<Voos> voos = voosRepository.findAll();
		return voos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	public VooResponseDTO listarPorId(UUID id) {
		Voos voo = voosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Voo não encontrado"));
		return convertToResponseDTO(voo);
	}

	private VooResponseDTO convertToResponseDTO(Voos voo) {
		VooResponseDTO dto = new VooResponseDTO();
		dto.setCodigoVoo(voo.getCodigoVoo());
		dto.setOrigem(voo.getOrigem());
		dto.setDestino(voo.getDestino());
		dto.setDataPartida(voo.getDataPartida());
		dto.setDataChegada(voo.getDataChegada());
		dto.setHoraPartida(voo.getHoraPartida() != null ? voo.getHoraPartida().toLocalTime() : null);
		dto.setHoraChegada(voo.getHoraChegada() != null ? voo.getHoraChegada().toLocalTime() : null);
		return dto;
	}
}
