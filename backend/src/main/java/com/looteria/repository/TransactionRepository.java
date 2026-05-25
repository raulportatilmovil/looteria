package com.looteria.repository;

import com.looteria.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Iterable<Transaction> findByComprador_IdUsuario(Long compradorId);

    Iterable<Transaction> findByVendedor_IdUsuario(Long vendedorId);

    Iterable<Transaction> findByPublicacion_IdPublicacion(Long publicacionId);

    long countByEstado(Transaction.TransactionStatus estado);
}
