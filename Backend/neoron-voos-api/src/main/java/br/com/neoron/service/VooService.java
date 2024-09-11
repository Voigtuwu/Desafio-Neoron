package br.com.neoron.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neoron.entity.Voos;
import br.com.neoron.exeception.CustomException;
import br.com.neoron.exeception.ResourceNotFoundException;
import br.com.neoron.repository.VoosRepository;

@Service
public class VooService {

	@Autowired
	VoosRepository voosRepository;

	@Transactional
	public Voos criarVoo(Voos voo) {
	    // Verificar se já existe um voo para o mesmo destino na mesma data
	    if (voosRepository.existsByDestinoAndDataPartida(voo.getDestino(), voo.getDataPartida())) {
	        throw new CustomException("Já existe um voo cadastrado para o destino " + voo.getDestino().getNome() + 
	                                  " na data " + voo.getDataPartida().toString() + ". Por favor, escolha outra data.");
	    }

	    // Verificar se há um conflito de horários entre os voos
	    LocalDateTime startTime = voo.getHoraPartida();
	    LocalDateTime endTime = voo.getHoraChegada();
	    if (voosRepository.existsConflictingFlights(voo.getOrigem(), startTime, endTime)) {
	        throw new CustomException("O voo a partir do aeroporto " + voo.getOrigem().getNome() + 
	                                  " entre " + startTime.toString() + " e " + endTime.toString() + 
	                                  " conflita com outro voo existente. Por favor, ajuste os horários.");
	    }

	    // Caso não haja conflitos, salvar o voo
	    return voosRepository.save(voo);
	}
    
    @Transactional
    public Voos atualizarVoo(UUID codigoVoo, Voos novoVoo) {
        Voos vooExistente = voosRepository.findById(codigoVoo)
                .orElseThrow(() -> new IllegalArgumentException("Voo não encontrado"));

        if (novoVoo.getOrigem() != null) vooExistente.setOrigem(novoVoo.getOrigem());
        if (novoVoo.getDestino() != null) vooExistente.setDestino(novoVoo.getDestino());
        if (novoVoo.getDataPartida() != null) vooExistente.setDataPartida(novoVoo.getDataPartida());
        if (novoVoo.getDataChegada() != null) vooExistente.setDataChegada(novoVoo.getDataChegada());
        if (novoVoo.getHoraPartida() != null) vooExistente.setHoraPartida(novoVoo.getHoraPartida());
        if (novoVoo.getHoraChegada() != null) vooExistente.setHoraChegada(novoVoo.getHoraChegada());

        LocalDateTime startTime = vooExistente.getHoraPartida();
        LocalDateTime endTime = vooExistente.getHoraChegada();
        if (voosRepository.existsConflictingFlights(vooExistente.getOrigem(), startTime, endTime)) {
            throw new IllegalArgumentException("Há um voo existente que se sobrepõe ao horário do voo atualizado.");
        }

        return voosRepository.save(vooExistente);
    }
	
	public void deletarVoo(UUID codigoVoo) {
		if (voosRepository.existsById(codigoVoo)) {
			voosRepository.deleteById(codigoVoo);
		} else {
			throw new ResourceNotFoundException("Voo com código " + codigoVoo + " não encontrado");
		}
	}
	
    public List<Voos> listarTodos() {
        return voosRepository.findAll();
    }
    
	public Voos listarPorId(UUID id) {
		return voosRepository.findById(id).orElse(null);
	}
	
}
