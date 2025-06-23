package io.github.higohenrique.super_heroi_api.dto;

import io.github.higohenrique.super_heroi_api.entity.Superpoder;
import lombok.Data;

@Data
public class SuperpoderResponseDTO {

    private Integer id;
    private String nome;
    private String descricao;

    public static SuperpoderResponseDTO fromEntity(Superpoder superpoder) {
        SuperpoderResponseDTO dto = new SuperpoderResponseDTO();
        dto.setId(superpoder.getId());
        dto.setNome(superpoder.getNome());
        dto.setDescricao(superpoder.getDescricao());
        return dto;
    }
}
