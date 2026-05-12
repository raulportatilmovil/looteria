package com.looteria.repository;

import com.looteria.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    Iterable<Review> findByAutor_IdUsuario(Long autorId);
    
    Iterable<Review> findByReceptor_IdUsuario(Long receptorId);
    
    Iterable<Review> findByTransaccion_IdTransaccion(Long transaccionId);
    
    List<Review> findByPublicacion_IdPublicacion(Long listingId);

    @Query("SELECT r FROM Review r JOIN r.transaccion t WHERE t.publicacion.idPublicacion = :listingId")
    List<Review> findByTransaccionPublicacionId(Long listingId);
}
