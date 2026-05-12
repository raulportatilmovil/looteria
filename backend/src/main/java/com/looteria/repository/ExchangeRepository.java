package com.looteria.repository;

import com.looteria.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    
    Iterable<Exchange> findBySolicitante_IdUsuario(Long solicitanteId);
    
    Iterable<Exchange> findBySolicitado_IdUsuario(Long solicitadoId);
    
    Iterable<Exchange> findByPublicacion_IdPublicacion(Long publicacionId);
}
