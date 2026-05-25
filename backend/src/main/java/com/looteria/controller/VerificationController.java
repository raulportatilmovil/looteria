package com.looteria.controller;

import com.looteria.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verificacion")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/enviar-codigo/{userId}")
    public ResponseEntity<?> enviarCodigo(@PathVariable Long userId) {
        try {
            verificationService.enviarCodigoVerificacion(userId);
            return ResponseEntity.ok(new SuccessResponse("Código de verificación enviado a tu email"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/verificar/{userId}")
    public ResponseEntity<?> verificarCodigo(@PathVariable Long userId, @RequestBody VerifyCodeRequest request) {
        try {
            verificationService.verificarCodigo(userId, request.getCodigo());
            return ResponseEntity.ok(new SuccessResponse("¡Verificación completada! Has recibido 100 puntos."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/estado/{userId}")
    public ResponseEntity<?> isVerificado(@PathVariable Long userId) {
        try {
            boolean verificado = verificationService.isVerificado(userId);
            return ResponseEntity.ok(new EstadoResponse(verificado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    static class VerifyCodeRequest {
        private String codigo;
        
        public String getCodigo() {
            return codigo;
        }
        
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
    }

    static class SuccessResponse {
        public String message;
        
        public SuccessResponse(String message) {
            this.message = message;
        }
    }

    static class ErrorResponse {
        public String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
    }

    static class EstadoResponse {
        public boolean verificado;
        
        public EstadoResponse(boolean verificado) {
            this.verificado = verificado;
        }
    }
}
