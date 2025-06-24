package io.github.higohenrique.super_heroi_api.controller;

import io.github.higohenrique.super_heroi_api.dto.ApiResponseDTO;
import io.github.higohenrique.super_heroi_api.dto.HeroiRequestDTO;
import io.github.higohenrique.super_heroi_api.dto.HeroiResponseDTO;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.github.higohenrique.super_heroi_api.service.HeroiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/herois")
public class HeroiController {

    @Autowired
    private HeroiService heroiService;

    @Operation(summary = "Cadastra um novo herói", description = "Cria um novo registro de herói no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Herói criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Herói com este 'nomeHeroi' já existe")
    })
    @PostMapping
    public ResponseEntity<HeroiResponseDTO> criarHeroi(@Valid @RequestBody HeroiRequestDTO heroiRequestDTO) {
        Heroi novoHeroi = heroiService.criarHeroi(heroiRequestDTO);
        return new ResponseEntity<>(HeroiResponseDTO.fromEntity(novoHeroi), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os heróis cadastrados", description = "Retorna uma lista com todos os heróis existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de heróis retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum herói encontrado")
    })
    @GetMapping
    public ResponseEntity<List<HeroiResponseDTO>> listarTodos() {
        List<HeroiResponseDTO> herois = heroiService.listarTodos();

        if (herois.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(herois);
    }

    @Operation(summary = "Busca um herói por seu ID", description = "Retorna os detalhes de um herói específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Herói encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Herói não encontrado para o ID informado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> buscarPorId(@PathVariable Integer id) {
        HeroiResponseDTO heroi = heroiService.buscarPorId(id);
        return ResponseEntity.ok(heroi);
    }

    @Operation(summary = "Atualiza um herói existente", description = "Atualiza os dados de um herói com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Herói atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Herói não encontrado para o ID informado"),
            @ApiResponse(responseCode = "409", description = "O 'nomeHeroi' fornecido já está em uso por outro herói")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> atualizarHeroi(@PathVariable Integer id, @Valid @RequestBody HeroiRequestDTO heroiRequestDTO) {
        HeroiResponseDTO heroiAtualizado = heroiService.atualizarHeroi(id, heroiRequestDTO);
        return ResponseEntity.ok(heroiAtualizado);
    }

    @Operation(summary = "Exclui um herói por seu ID", description = "Remove o registro de um herói do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Herói excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Herói não encontrado para o ID informado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deletarHeroi(@PathVariable Integer id) {

        heroiService.deletarHeroi(id);
        ApiResponseDTO response = new ApiResponseDTO("Herói com o ID " + id + " foi excluído com sucesso.");

        return ResponseEntity.ok(response);
    }

}
