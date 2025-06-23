package io.github.higohenrique.super_heroi_api.repository;

import io.github.higohenrique.super_heroi_api.entity.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Integer> {

    boolean existsByNomeHeroi(String nomeHeroi);

    Optional<Heroi> findByNomeHeroi(String nomeHeroi);
}
