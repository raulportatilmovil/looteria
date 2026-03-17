package com.looteria.repository;

import com.looteria.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    Iterable<Image> findByPublicacion_IdPublicacion(Long publicacionId);
}
