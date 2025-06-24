package io.github.higohenrique.super_heroi_api.service;

import io.github.higohenrique.super_heroi_api.dto.SuperpoderResponseDTO;
import io.github.higohenrique.super_heroi_api.repository.SuperpoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperpoderService {

    @Autowired
    private SuperpoderRepository superpoderRepository;

    public List<SuperpoderResponseDTO> listarTodos() {

        return superpoderRepository.findAll()
                .stream()
                .map(SuperpoderResponseDTO::fromEntity) // Reutilizando nosso método de conversão
                .collect(Collectors.toList());
    }
}
