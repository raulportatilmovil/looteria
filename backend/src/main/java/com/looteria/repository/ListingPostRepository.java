package com.looteria.repository;

import com.looteria.entity.ListingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingPostRepository extends JpaRepository<ListingPost, Long> {
    
    Iterable<ListingPost> findByUsuario_IdUsuario(Long usuarioId);
    
    Iterable<ListingPost> findByProducto_IdProducto(Long productoId);
    
    Iterable<ListingPost> findByEstadoPublicacion(ListingPost.PublicationStatus estado);
}
