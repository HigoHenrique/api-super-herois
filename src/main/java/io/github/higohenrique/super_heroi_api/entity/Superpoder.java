package io.github.higohenrique.super_heroi_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "superpoderes")
public class Superpoder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "superpoder", length = 50, nullable = false, unique = true)
    private String nome;

    @Column(length = 250)
    private String descricao;

}
