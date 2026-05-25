package com.looteria.repository;

import com.looteria.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    
    Optional<VerificationCode> findByUsuario_IdUsuarioAndUsadoFalse(Long userId);
    
    Optional<VerificationCode> findByUsuario_IdUsuarioAndCodigoAndUsadoFalse(Long userId, String codigo);
}
