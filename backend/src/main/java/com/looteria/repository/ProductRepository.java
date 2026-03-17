package com.looteria.repository;

import com.looteria.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findByTitulo(String titulo);
    
    Iterable<Product> findByPlataforma_IdCategoria(Long plataformaId);
    
    Iterable<Product> findByTipoArticulo_IdCategoria(Long tipoArticuloId);
    
    Iterable<Product> findByFranquicia_IdCategoria(Long franquiciaId);
}
