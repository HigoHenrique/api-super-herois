package io.github.higohenrique.super_heroi_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "herois")
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(length = 120, nullable = false, unique = true)
    private String nomeHeroi;

    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double peso;

    @ManyToMany
    @JoinTable(
            name = "heroissuperpoderes",
            joinColumns = @JoinColumn(name = "heroi_id"),
            inverseJoinColumns = @JoinColumn(name = "superpoder_id")
    )
    private Set<Superpoder> superpoderes = new HashSet<>();
}
