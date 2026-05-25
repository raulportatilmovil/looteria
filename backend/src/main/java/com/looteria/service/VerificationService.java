package com.looteria.service;

import com.looteria.entity.User;
import com.looteria.entity.VerificationCode;
import com.looteria.repository.UserRepository;
import com.looteria.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class VerificationService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private static final SecureRandom random = new SecureRandom();
    private static final int CODE_LENGTH = 6;
    private static final int VERIFICATION_POINTS = 100;

    @Transactional
    public void enviarCodigoVerificacion(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getVerificadoIdentidad()) {
            throw new RuntimeException("El usuario ya está verificado");
        }

        // Invalidar códigos anteriores no usados
        Optional<VerificationCode> codigoExistente = verificationCodeRepository.findByUsuario_IdUsuarioAndUsadoFalse(userId);
        codigoExistente.ifPresent(code -> {
            code.setUsado(true);
            verificationCodeRepository.save(code);
        });

        // Generar nuevo código
        String codigo = generarCodigo();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setUsuario(user);
        verificationCode.setCodigo(codigo);
        verificationCodeRepository.save(verificationCode);

        // Enviar email
        emailService.sendVerificationCode(user.getEmail(), user.getNombreUsuario(), codigo);
    }

    @Transactional
    public void verificarCodigo(Long userId, String codigo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getVerificadoIdentidad()) {
            throw new RuntimeException("El usuario ya está verificado");
        }

        VerificationCode verificationCode = verificationCodeRepository
                .findByUsuario_IdUsuarioAndCodigoAndUsadoFalse(userId, codigo)
                .orElseThrow(() -> new RuntimeException("Código inválido o no encontrado"));

        if (verificationCode.isExpirado()) {
            throw new RuntimeException("El código ha expirado. Solicita uno nuevo.");
        }

        // Marcar código como usado
        verificationCode.setUsado(true);
        verificationCodeRepository.save(verificationCode);

        // Verificar usuario y añadir puntos
        user.setVerificadoIdentidad(true);
        user.setPuntosAcumulados(user.getPuntosAcumulados() + VERIFICATION_POINTS);
        userRepository.save(user);
    }

    public boolean isVerificado(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return user.getVerificadoIdentidad();
    }

    private String generarCodigo() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
