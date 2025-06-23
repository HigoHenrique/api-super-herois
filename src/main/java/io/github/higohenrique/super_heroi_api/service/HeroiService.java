package io.github.higohenrique.super_heroi_api.service;

import io.github.higohenrique.super_heroi_api.dto.HeroiRequestDTO;
import io.github.higohenrique.super_heroi_api.dto.HeroiResponseDTO;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.github.higohenrique.super_heroi_api.entity.Superpoder;
import io.github.higohenrique.super_heroi_api.exception.InvalidRequestException;
import io.github.higohenrique.super_heroi_api.exception.ResourceConflictException;
import io.github.higohenrique.super_heroi_api.exception.ResourceNotFoundException;
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

    public List<HeroiResponseDTO> listarTodos() {
        List<Heroi> herois = heroiRepository.findAll();

        return herois.stream()
                .map(HeroiResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public HeroiResponseDTO buscarPorId(Integer id) {
        Heroi heroi = heroiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Herói não encontrado com o ID: " + id));

        return HeroiResponseDTO.fromEntity(heroi);
    }

    public HeroiResponseDTO atualizarHeroi(Integer id, HeroiRequestDTO heroiRequestDTO) {

        Heroi heroiExistente = heroiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Herói não encontrado com o ID: " + id));

        heroiRepository.findByNomeHeroi(heroiRequestDTO.getNomeHeroi())
                .ifPresent(heroiComMesmoNome -> {
                    if (!heroiComMesmoNome.getId().equals(id)) {
                        throw new ResourceConflictException("O nome de herói '" + heroiRequestDTO.getNomeHeroi() +
                                "' já está em uso por outro herói.");
                    }
                });

        Set<Integer> superpoderesIds = heroiRequestDTO.getSuperpoderesIds();
        Set<Superpoder> superpoderesEncontrados = new HashSet<>(superpoderRepository.findAllById(superpoderesIds));

        if (superpoderesEncontrados.size() != superpoderesIds.size()) {
            Set<Integer> idsEncontrados = superpoderesEncontrados.stream().map(Superpoder::getId).collect(Collectors.toSet());
            List<Integer> idsInvalidos = superpoderesIds.stream()
                    .filter(idSuperpoder -> !idsEncontrados.contains(idSuperpoder)).toList();

            throw new InvalidRequestException("Os seguintes IDs de superpoderes são inválidos ou não existem: " + idsInvalidos);
        }

        heroiExistente.setNome(heroiRequestDTO.getNome());
        heroiExistente.setNomeHeroi(heroiRequestDTO.getNomeHeroi());
        heroiExistente.setDataNascimento(heroiRequestDTO.getDataNascimento());
        heroiExistente.setAltura(heroiRequestDTO.getAltura());
        heroiExistente.setPeso(heroiRequestDTO.getPeso());
        heroiExistente.setSuperpoderes(superpoderesEncontrados);

        Heroi heroiAtualizado = heroiRepository.save(heroiExistente);

        return HeroiResponseDTO.fromEntity(heroiAtualizado);
    }
}
