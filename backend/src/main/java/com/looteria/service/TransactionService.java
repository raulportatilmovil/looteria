package com.looteria.service;

import com.looteria.entity.ListingPost;
import com.looteria.entity.Transaction;
import com.looteria.entity.User;
import com.looteria.repository.ListingPostRepository;
import com.looteria.repository.TransactionRepository;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Transaction createTransaction(Long publicacionId, Long compradorId, Long vendedorId,
                                        String tipo, BigDecimal precioFinal) {
        ListingPost listing = listingPostRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        User comprador = userRepository.findById(compradorId)
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));

        User vendedor = userRepository.findById(vendedorId)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        Transaction transaction = new Transaction();
        transaction.setPublicacion(listing);
        transaction.setComprador(comprador);
        transaction.setVendedor(vendedor);
        transaction.setTipo(Transaction.TransactionType.valueOf(tipo));
        transaction.setPrecioFinal(precioFinal);
        transaction.setEstado(Transaction.TransactionStatus.PENDIENTE);
        transaction.setFechaTransaccion(LocalDateTime.now());

        // Calcular comisión (10% del precio final)
        BigDecimal comision = precioFinal.multiply(new BigDecimal("0.10"));
        transaction.setComision(comision);

        Transaction savedTransaction = transactionRepository.save(transaction);

        listing.setEstadoPublicacion(ListingPost.PublicationStatus.VENDIDA);
        listingPostRepository.save(listing);

        return savedTransaction;
    }

    @Transactional(readOnly = true)
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Iterable<Transaction> getTransactionsByBuyer(Long buyerId) {
        return transactionRepository.findByComprador_IdUsuario(buyerId);
    }

    @Transactional(readOnly = true)
    public Iterable<Transaction> getTransactionsBySeller(Long sellerId) {
        return transactionRepository.findByVendedor_IdUsuario(sellerId);
    }

    @Transactional
    public Transaction updateTransactionStatus(Long transactionId, String newStatus) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        transaction.setEstado(Transaction.TransactionStatus.valueOf(newStatus));
        return transactionRepository.save(transaction);
    }
}
