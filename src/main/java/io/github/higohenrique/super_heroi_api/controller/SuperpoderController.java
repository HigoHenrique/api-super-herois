package io.github.higohenrique.super_heroi_api.controller;

import io.github.higohenrique.super_heroi_api.dto.SuperpoderResponseDTO;
import io.github.higohenrique.super_heroi_api.service.SuperpoderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/superpoderes")
public class SuperpoderController {

    @Autowired
    private SuperpoderService superpoderService;

    @Operation(summary = "Lista todos os superpoderes disponíveis",
            description = "Retorna uma lista de todos os superpoderes que podem ser associados a um herói.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de superpoderes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum superpoder encontrado (a tabela está vazia)")
    })
    @GetMapping
    public ResponseEntity<List<SuperpoderResponseDTO>> listarTodos() {
        List<SuperpoderResponseDTO> superpoderes = superpoderService.listarTodos();

        if (superpoderes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(superpoderes);
    }
}
