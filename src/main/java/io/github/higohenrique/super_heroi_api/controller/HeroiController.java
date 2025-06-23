package io.github.higohenrique.super_heroi_api.controller;

import io.github.higohenrique.super_heroi_api.dto.HeroiRequestDTO;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.github.higohenrique.super_heroi_api.service.HeroiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
