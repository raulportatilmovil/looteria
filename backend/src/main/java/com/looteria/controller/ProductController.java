package com.looteria.controller;

import com.looteria.entity.Product;
import com.looteria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    /**
     * GET /api/productos - Listar todos los productos
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    /**
     * GET /api/productos/{id} - Obtener producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado");
        }
    }
    
    /**
     * GET /api/productos/buscar/titulo?titulo=NombreJuego - Buscar por título
     */
    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> getProductByTitulo(@RequestParam String titulo) {
        Optional<Product> product = productService.getProductByTitulo(titulo);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con título '" + titulo + "' no encontrado");
        }
    }
    
    /**
     * GET /api/productos/plataforma/{plataformaId} - Obtener productos por plataforma
     */
    @GetMapping("/plataforma/{plataformaId}")
    public ResponseEntity<?> getProductsByPlataforma(@PathVariable Long plataformaId) {
        Iterable<Product> products = productService.getProductsByPlataforma(plataformaId);
        return ResponseEntity.ok(products);
    }
    
    /**
     * GET /api/productos/tipo/{tipoId} - Obtener productos por tipo de artículo
     */
    @GetMapping("/tipo/{tipoId}")
    public ResponseEntity<?> getProductsByTipo(@PathVariable Long tipoId) {
        Iterable<Product> products = productService.getProductsByTipoArticulo(tipoId);
        return ResponseEntity.ok(products);
    }
    
    /**
     * GET /api/productos/franquicia/{franquiciaId} - Obtener productos por franquicia
     */
    @GetMapping("/franquicia/{franquiciaId}")
    public ResponseEntity<?> getProductsByFranquicia(@PathVariable Long franquiciaId) {
        Iterable<Product> products = productService.getProductsByFranquicia(franquiciaId);
        return ResponseEntity.ok(products);
    }
    
    /**
     * POST /api/productos - Crear nuevo producto
     */
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            // Validaciones básicas
            if (product.getTitulo() == null || product.getTitulo().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El título del producto es requerido");
            }
            
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el producto: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/productos/{id} - Actualizar producto
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            
            if (updatedProduct != null) {
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el producto: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/productos/{id} - Eliminar producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            boolean deleted = productService.deleteProduct(id);
            
            if (deleted) {
                return ResponseEntity.ok("Producto eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el producto: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/productos/stats/total - Obtener total de productos
     */
    @GetMapping("/stats/total")
    public ResponseEntity<?> getTotalProducts() {
        long total = productService.countProducts();
        return ResponseEntity.ok("{\"total\": " + total + "}");
    }
}
