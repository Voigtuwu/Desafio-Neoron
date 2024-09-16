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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/aeroportos")
public class AeroportosController {

    @Autowired
    private AeroportoService aeroportoService;

    @Operation(summary = "Cria um novo aeroporto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aeroporto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public Aeroportos criarAeroporto(@RequestBody Aeroportos a) {
        return aeroportoService.criarAeroporto(a);
    }

    @Operation(summary = "Atualiza um aeroporto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aeroporto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aeroporto não encontrado")
    })
    @PutMapping("/{id}")
    public Aeroportos atualizarAeroporto(@PathVariable Long id, @RequestBody Aeroportos a) {
        return aeroportoService.atualizarAeroporto(id, a);
    }

    @Operation(summary = "Deleta um aeroporto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aeroporto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aeroporto não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletarAeroporto(@PathVariable Long id) {
        aeroportoService.deletarAeroporto(id);
    }

    @Operation(summary = "Obtém um aeroporto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aeroporto encontrado"),
        @ApiResponse(responseCode = "404", description = "Aeroporto não encontrado")
    })
    @GetMapping("/{id}")
    public Aeroportos listarPorId(@PathVariable Long id) {
        return aeroportoService.listarPorId(id);
    }

    @Operation(summary = "Obtém todos os aeroportos")
    @ApiResponse(responseCode = "200", description = "Lista de aeroportos retornada com sucesso")
    @GetMapping
    public List<Aeroportos> listarTodos() {
        return aeroportoService.listarTodos();
    }
}
