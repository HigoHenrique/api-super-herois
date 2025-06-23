package io.github.higohenrique.super_heroi_api.service;

import io.github.higohenrique.super_heroi_api.dto.HeroiRequestDTO;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.github.higohenrique.super_heroi_api.entity.Superpoder;
import io.github.higohenrique.super_heroi_api.exception.InvalidRequestException;
import io.github.higohenrique.super_heroi_api.exception.ResourceConflictException;
import io.github.higohenrique.super_heroi_api.repository.HeroiRepository;
import io.github.higohenrique.super_heroi_api.repository.SuperpoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    @Autowired
    private SuperpoderRepository superpoderRepository;

    public Heroi criarHeroi(HeroiRequestDTO heroiRequestDTO) {

        if (heroiRepository.existsByNomeHeroi(heroiRequestDTO.getNomeHeroi())) {
            throw new ResourceConflictException("Já existe um herói cadastrado com o nome '" + heroiRequestDTO.getNomeHeroi() + "'.");
        }

        Set<Integer> superpoderesIds = heroiRequestDTO.getSuperpoderesIds();
        Set<Superpoder> superpoderesEncontrados = new HashSet<>(superpoderRepository.findAllById(superpoderesIds));

        if(superpoderesEncontrados.size() != superpoderesIds.size()) {

            Set<Integer> idsEncontrados = superpoderesEncontrados.stream()
                    .map(Superpoder::getId)
                    .collect(Collectors.toSet());

            List<Integer> idsInvalidos = superpoderesIds.stream()
                    .filter(id -> !idsEncontrados.contains(id))
                    .toList();

            throw new InvalidRequestException("Os seguintes IDs de superpoderes são inválidos ou não existem: " + idsInvalidos);

        }

        Heroi heroi = new Heroi();
        heroi.setNome(heroiRequestDTO.getNome());
        heroi.setNomeHeroi(heroiRequestDTO.getNomeHeroi());
        heroi.setDataNascimento(heroiRequestDTO.getDataNascimento());
        heroi.setAltura(heroiRequestDTO.getAltura());
        heroi.setPeso(heroiRequestDTO.getPeso());
        heroi.setSuperpoderes(superpoderesEncontrados);

        return heroiRepository.save(heroi);
    }
}
