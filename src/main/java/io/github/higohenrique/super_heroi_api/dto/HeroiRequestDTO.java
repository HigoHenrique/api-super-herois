package io.github.higohenrique.super_heroi_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class HeroiRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio.")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
    private String nome;

    @NotBlank(message = "O nome de herói não pode ser nulo ou vazio.")
    @Size(max = 120, message = "O nome de herói deve ter no máximo 120 caracteres.")
    private String nomeHeroi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "A data de nascimento não pode ser uma data futura.")
    private LocalDate dataNascimento;

    @NotNull(message = "A altura não pode ser nula.")
    @Positive(message = "A altura deve ser um valor positivo.")
    private Double altura;

    @NotNull(message = "O peso não pode ser nulo.")
    @Positive(message = "O peso deve ser um valor positivo.")
    private Double peso;

    @NotEmpty(message = "A lista de IDs de superpoderes não pode ser vazia.")
    private Set<Integer> superpoderesIds;
}
