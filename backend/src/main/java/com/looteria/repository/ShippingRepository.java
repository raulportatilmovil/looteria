package com.looteria.repository;

import com.looteria.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    
    Iterable<Shipping> findByTransaccion_IdTransaccion(Long transaccionId);
    
    Iterable<Shipping> findByEstado(Shipping.ShippingStatus estado);
}
