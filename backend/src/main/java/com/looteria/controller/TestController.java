package com.looteria.controller;

import com.looteria.entity.User;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/connection")
    public ResponseEntity<Map<String, String>> testConnection() {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("status", "CONECTADO");
            response.put("message", "Conexión exitosa a la base de datos Looteria");
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error en la conexión: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        try {
            List<User> usuarios = userRepository.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "ÉXITO");
            response.put("cantidad", usuarios.size());
            response.put("usuarios", usuarios);
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al consultar usuarios: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("/usuarios/count")
    public ResponseEntity<Map<String, Object>> countUsers() {
        try {
            long count = userRepository.count();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "ÉXITO");
            response.put("totalUsuarios", count);
            response.put("message", "Se encontraron " + count + " usuarios en la base de datos");
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al contar usuarios: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getStatus() {
        try {
            long userCount = userRepository.count();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "APLICACIÓN EN LÍNEA");
            response.put("baseDatos", "CONECTADA");
            response.put("totalUsuarios", userCount);
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("baseDatos", "DESCONECTADA");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
