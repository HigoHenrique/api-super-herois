package io.github.higohenrique.super_heroi_api.repository;

import io.github.higohenrique.super_heroi_api.entity.Superpoder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperpoderRepository extends JpaRepository<Superpoder, Integer> {
}
