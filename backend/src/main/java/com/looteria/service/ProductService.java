package com.looteria.service;

import com.looteria.entity.Product;
import com.looteria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    /**
     * Crear un nuevo producto
     */
    public Product createProduct(Product product) {
        if (product.getFechaCreacion() == null) {
            product.setFechaCreacion(LocalDateTime.now());
        }
        return productRepository.save(product);
    }
    
    /**
     * Obtener producto por ID
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    /**
     * Listar todos los productos
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Buscar producto por título
     */
    public Optional<Product> getProductByTitulo(String titulo) {
        return productRepository.findByTitulo(titulo);
    }
    
    /**
     * Obtener productos por plataforma
     */
    public Iterable<Product> getProductsByPlataforma(Long plataformaId) {
        return productRepository.findByPlataforma_IdCategoria(plataformaId);
    }
    
    /**
     * Obtener productos por tipo de artículo
     */
    public Iterable<Product> getProductsByTipoArticulo(Long tipoArticuloId) {
        return productRepository.findByTipoArticulo_IdCategoria(tipoArticuloId);
    }
    
    /**
     * Obtener productos por franquicia
     */
    public Iterable<Product> getProductsByFranquicia(Long franquiciaId) {
        return productRepository.findByFranquicia_IdCategoria(franquiciaId);
    }
    
    /**
     * Actualizar producto
     */
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);
        
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            
            if (productDetails.getTitulo() != null) {
                product.setTitulo(productDetails.getTitulo());
            }
            if (productDetails.getDescripcion() != null) {
                product.setDescripcion(productDetails.getDescripcion());
            }
            if (productDetails.getPlataforma() != null) {
                product.setPlataforma(productDetails.getPlataforma());
            }
            if (productDetails.getTipoArticulo() != null) {
                product.setTipoArticulo(productDetails.getTipoArticulo());
            }
            if (productDetails.getFranquicia() != null) {
                product.setFranquicia(productDetails.getFranquicia());
            }
            if (productDetails.getFechaLanzamiento() != null) {
                product.setFechaLanzamiento(productDetails.getFechaLanzamiento());
            }
            if (productDetails.getValorEstimado() != null) {
                product.setValorEstimado(productDetails.getValorEstimado());
            }
            
            return productRepository.save(product);
        }
        
        return null;
    }
    
    /**
     * Eliminar producto
     */
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Contar total de productos
     */
    public long countProducts() {
        return productRepository.count();
    }
}
