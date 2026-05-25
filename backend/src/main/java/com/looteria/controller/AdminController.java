package com.looteria.controller;

import com.looteria.dto.UserDTO;
import com.looteria.dto.ListingDetailDTO;
import com.looteria.service.UserService;
import com.looteria.service.ListingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListingAdminService listingAdminService;

    // ENDPOINTS USUARIOS

    /**
     * Obtener todos los usuarios
     */
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsersDTO();
        return ResponseEntity.ok(users);
    }

    /**
     * Obtener usuario por ID
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserByIdDTO(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Eliminar usuario
     */
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ENDPOINTS PUBLICACIONES

    /**
     * Obtener todas las publicaciones
     */
    @GetMapping("/publicaciones")
    public ResponseEntity<List<ListingDetailDTO>> getAllListings() {
        List<ListingDetailDTO> listings = listingAdminService.getAllListingsForAdmin();
        return ResponseEntity.ok(listings);
    }

    /**
     * GET /admin/publicaciones/usuario/{userId} - Obtener publicaciones de un usuario
     */
    @GetMapping("/publicaciones/usuario/{userId}")
    public ResponseEntity<List<ListingDetailDTO>> getListingsByUser(@PathVariable Long userId) {
        List<ListingDetailDTO> listings = listingAdminService.getListingsByUserId(userId);
        return ResponseEntity.ok(listings);
    }

    /**
     * GET /admin/publicaciones/{id} - Obtener publicación por ID
     */
    @GetMapping("/publicaciones/{id}")
    public ResponseEntity<ListingDetailDTO> getListingById(@PathVariable Long id) {
        ListingDetailDTO listing = listingAdminService.getListingById(id);
        if (listing == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listing);
    }

    /**
     * DELETE /admin/publicaciones/{id} - Eliminar publicación
     */
    @DeleteMapping("/publicaciones/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        try {
            listingAdminService.deleteListing(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
