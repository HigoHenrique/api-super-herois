package io.github.higohenrique.super_heroi_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.higohenrique.super_heroi_api.entity.Heroi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class HeroiResponseDTO {

    private Integer id;
    private String nome;
    private String nomeHeroi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento do herói no formato dd/MM/yyyy",
            example = "09/02/1995", type = "string")
    private LocalDate dataNascimento;

    private Double altura;
    private Double peso;
    private Set<SuperpoderResponseDTO> superpoderes;

    public static HeroiResponseDTO fromEntity(Heroi heroi) {
        HeroiResponseDTO dto = new HeroiResponseDTO();
        dto.setId(heroi.getId());
        dto.setNome(heroi.getNome());
        dto.setNomeHeroi(heroi.getNomeHeroi());
        dto.setDataNascimento(heroi.getDataNascimento());
        dto.setAltura(heroi.getAltura());
        dto.setPeso(heroi.getPeso());

        dto.setSuperpoderes(
                heroi.getSuperpoderes().stream()
                        .map(SuperpoderResponseDTO::fromEntity)
                        .collect(Collectors.toSet())
        );

        return dto;
    }
}
