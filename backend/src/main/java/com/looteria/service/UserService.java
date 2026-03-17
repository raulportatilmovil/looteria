package com.looteria.service;

import com.looteria.entity.User;
import com.looteria.entity.UserRole;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(String email, String nombreUsuario, String contrasena) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (userRepository.existsByNombreUsuario(nombreUsuario)) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        User nuevoUsuario = new User();
        nuevoUsuario.setIdUsuario(null);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setRol(UserRole.REGISTRADO);
        
        return userRepository.save(nuevoUsuario);
    }
    
    public User loginUser(String email, String contrasena) {
        Optional<User> usuarioOpt = userRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioOpt.get();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public long countUsers() {
        return userRepository.count();
    }
}
