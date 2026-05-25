package com.looteria.repository;

import com.looteria.entity.PointsRedemption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointsRedemptionRepository extends CrudRepository<PointsRedemption, Long> {
    List<PointsRedemption> findByUsuario_IdUsuario(Long userId);
    Optional<PointsRedemption> findByCodigo(String codigo);
}
