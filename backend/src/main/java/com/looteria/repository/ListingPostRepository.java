package com.looteria.repository;

import com.looteria.dto.ListingSearchProjection;
import com.looteria.entity.ListingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingPostRepository extends JpaRepository<ListingPost, Long> {
    
    Iterable<ListingPost> findByUsuario_IdUsuario(Long usuarioId);
    
    Iterable<ListingPost> findByProducto_IdProducto(Long productoId);
    
    @Query(value = "SELECT " +
           "lp.id_publicacion AS idPublicacion, " +
           "p.id_producto AS idProducto, " +
           "p.titulo AS titulo, " +
           "p.descripcion AS descripcion, " +
           "plat.nombre AS plataformaNombre, " +
           "tipo.nombre AS tipoArticuloNombre, " +
           "franq.nombre AS franquiciaNombre, " +
           "lp.precio AS precio, " +
           "est.nombre AS estadoArticuloNombre, " +
           "lp.tipo_transaccion AS tipoTransaccion, " +
           "reg.nombre AS regionNombre, " +
           "u.nombre_usuario AS nombreUsuario, " +
           "lp.descripcion_estado AS descripcionEstado, " +
           "idioma.nombre AS idiomaNombre, " +
           "lp.fecha_creacion AS fechaCreacion " +
           "FROM publicaciones lp " +
           "LEFT JOIN productos p ON lp.id_producto = p.id_producto " +
           "LEFT JOIN categorias plat ON p.plataforma_id = plat.id_categoria " +
           "LEFT JOIN categorias tipo ON p.tipo_articulo_id = tipo.id_categoria " +
           "LEFT JOIN categorias franq ON p.franquicia_id = franq.id_categoria " +
           "LEFT JOIN categorias est ON lp.estado_articulo_id = est.id_categoria " +
           "LEFT JOIN categorias reg ON lp.region_id = reg.id_categoria " +
           "LEFT JOIN categorias idioma ON lp.idioma_id = idioma.id_categoria " +
           "LEFT JOIN usuarios u ON lp.id_usuario = u.id_usuario " +
           "WHERE lp.estado_publicacion = 'ACTIVA' " +
           "ORDER BY lp.fecha_creacion DESC",
           nativeQuery = true)
    Iterable<ListingSearchProjection> findAllActiveListings();
}
