package com.looteria.controller;

import com.looteria.dto.CreateTransactionRequestDTO;
import com.looteria.entity.Transaction;
import com.looteria.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ─── Crear nueva transacción ─────────────────────────────────────────────────

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequestDTO request) {
        try {
            Transaction transaction = transactionService.createTransaction(
                    request.getPublicacionId(),
                    request.getCompradorId(),
                    request.getVendedorId(),
                    request.getTipo(),
                    request.getPrecioFinal()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // ─── Obtener transacción por ID ──────────────────────────────────────────────

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        try {
            Optional<Transaction> transaction = transactionService.getTransactionById(id);
            if (transaction.isPresent()) {
                return ResponseEntity.ok(transaction.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Transacción no encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // ─── Obtener transacciones del comprador ─────────────────────────────────────

    @GetMapping("/comprador/{compradorId}")
    public ResponseEntity<?> getTransactionsByBuyer(@PathVariable Long compradorId) {
        try {
            List<Transaction> transactions = StreamSupport.stream(
                    transactionService.getTransactionsByBuyer(compradorId).spliterator(), false
            ).toList();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // ─── Obtener transacciones del vendedor ──────────────────────────────────────

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<?> getTransactionsBySeller(@PathVariable Long vendedorId) {
        try {
            List<Transaction> transactions = StreamSupport.stream(
                    transactionService.getTransactionsBySeller(vendedorId).spliterator(), false
            ).toList();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // ─── Actualizar estado de transacción ────────────────────────────────────────

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> updateTransactionStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        try {
            Transaction transaction = transactionService.updateTransactionStatus(id, request.getEstado());
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // ─── Response classes ────────────────────────────────────────────────────────

    static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }

    static class SuccessResponse {
        public String message;

        public SuccessResponse(String message) {
            this.message = message;
        }
    }

    static class StatusUpdateRequest {
        private String estado;

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}
