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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/voos")
public class VoosController {

    @Autowired
    private VooService voosService;

    @Operation(summary = "Cria um novo voo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Voo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<VooResponseDTO> criarVoo(@RequestBody Voos v) {
        VooResponseDTO vooCriado = voosService.criarVoo(v);
        return new ResponseEntity<>(vooCriado, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um voo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voo atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Voo não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VooResponseDTO> atualizarVoo(@PathVariable UUID id, @RequestBody Voos v) {
        VooResponseDTO vooAtualizado = voosService.atualizarVoo(id, v);
        return new ResponseEntity<>(vooAtualizado, HttpStatus.OK);
    }

    @Operation(summary = "Obtém todos os voos")
    @ApiResponse(responseCode = "200", description = "Lista de voos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<VooResponseDTO>> listarTodos() {
        List<VooResponseDTO> voos = voosService.listarTodos();
        return new ResponseEntity<>(voos, HttpStatus.OK);
    }

    @Operation(summary = "Obtém um voo pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voo encontrado"),
        @ApiResponse(responseCode = "404", description = "Voo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VooResponseDTO> listarPorId(@PathVariable UUID id) {
        VooResponseDTO voo = voosService.listarPorId(id);
        return new ResponseEntity<>(voo, HttpStatus.OK);
    }

    @Operation(summary = "Deleta um voo pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Voo deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Voo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVoo(@PathVariable UUID id) {
        try {
            voosService.deletarVoo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
