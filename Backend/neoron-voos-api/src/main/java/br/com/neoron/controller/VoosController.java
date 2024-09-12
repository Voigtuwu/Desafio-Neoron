package br.com.neoron.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neoron.dto.VooResponseDTO;
import br.com.neoron.entity.Voos;
import br.com.neoron.service.VooService;

@RestController
@RequestMapping("/api/voos")
public class VoosController {

	@Autowired
	private VooService voosService;

	@PostMapping
	public ResponseEntity<VooResponseDTO> criarVoo(@RequestBody Voos v) {
		VooResponseDTO vooCriado = voosService.criarVoo(v);
		return new ResponseEntity<>(vooCriado, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VooResponseDTO> atualizarVoo(@PathVariable UUID id, @RequestBody Voos v) {
	    VooResponseDTO vooAtualizado = voosService.atualizarVoo(id, v);
	    return new ResponseEntity<>(vooAtualizado, HttpStatus.OK);
	}

    @GetMapping
    public ResponseEntity<List<VooResponseDTO>> listarTodos() {
        List<VooResponseDTO> voos = voosService.listarTodos();
        return new ResponseEntity<>(voos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VooResponseDTO> listarPorId(@PathVariable UUID id) {
        VooResponseDTO voo = voosService.listarPorId(id);
        return new ResponseEntity<>(voo, HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarVoo(@PathVariable UUID id) {
		try {
			voosService.deletarVoo(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// Trata a exceção e retorna um erro adequado
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}