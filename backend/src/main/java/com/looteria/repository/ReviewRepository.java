package com.looteria.repository;

import com.looteria.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    Iterable<Review> findByAutor_IdUsuario(Long autorId);
    
    Iterable<Review> findByReceptor_IdUsuario(Long receptorId);
    
    Iterable<Review> findByTransaccion_IdTransaccion(Long transaccionId);
}
