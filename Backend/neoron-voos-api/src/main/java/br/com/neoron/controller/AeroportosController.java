package br.com.neoron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neoron.entity.Aeroportos;
import br.com.neoron.service.AeroportoService;

@RestController
@RequestMapping("/api/aeroportos")
public class AeroportosController {

	@Autowired
	private AeroportoService aeroportoService;

	// Método para cadastrar aeroporto
	@PostMapping
	public Aeroportos criarAeroporto(@RequestBody Aeroportos a) {
		return aeroportoService.criarAeroporto(a);
	}

	// Método para atualizar aeroporto
	@PutMapping("/{id}")
	public Aeroportos atualizarAeroporto(@PathVariable Long id, @RequestBody Aeroportos a) {
		return aeroportoService.atualizarAeroporto(id, a);
	}

	// Método para deletar
	@DeleteMapping("/{id}")
	public void deletarAeroporto(@PathVariable Long id) {
		aeroportoService.deletarAeroporto(id);
	}

	// Método para listar por id
	@GetMapping("/{id}")
	public Aeroportos listarPorId(@PathVariable Long id) {
		return aeroportoService.listarPorId(id);
	}

	// Método para listar todos
	@GetMapping
	public List<Aeroportos> listarTodos() {
		return aeroportoService.listarTodos();
	}
}
