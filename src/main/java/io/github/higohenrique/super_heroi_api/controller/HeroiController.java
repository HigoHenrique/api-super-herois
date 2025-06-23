package io.github.higohenrique.super_heroi_api.controller;

import io.github.higohenrique.super_heroi_api.dto.HeroiRequestDTO;
import io.github.higohenrique.super_heroi_api.dto.HeroiResponseDTO;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.github.higohenrique.super_heroi_api.service.HeroiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herois")
public class HeroiController {

    @Autowired
    private HeroiService heroiService;

    @PostMapping
    public ResponseEntity<Heroi> criarHeroi(@Valid @RequestBody HeroiRequestDTO heroiRequestDTO) {
        Heroi novoHeroi = heroiService.criarHeroi(heroiRequestDTO);
        return new ResponseEntity<>(novoHeroi, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HeroiResponseDTO>> listarTodos() {
        List<HeroiResponseDTO> herois = heroiService.listarTodos();

        if (herois.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(herois);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> buscarPorId(@PathVariable Integer id) {
        HeroiResponseDTO heroi = heroiService.buscarPorId(id);
        return ResponseEntity.ok(heroi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> atualizarHeroi(@PathVariable Integer id, @Valid @RequestBody HeroiRequestDTO heroiRequestDTO) {
        HeroiResponseDTO heroiAtualizado = heroiService.atualizarHeroi(id, heroiRequestDTO);
        return ResponseEntity.ok(heroiAtualizado);
    }

}
