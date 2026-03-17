package com.looteria.repository;

import com.looteria.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByNombre(String nombre);
    
    Iterable<Category> findByTipo(String tipo);
    
    boolean existsByNombre(String nombre);
}
