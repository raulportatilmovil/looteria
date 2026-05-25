package com.looteria.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.looteria.dto.ListingDetailDTO;
import com.looteria.entity.Image;
import com.looteria.entity.ListingPost;
import com.looteria.repository.ImageRepository;
import com.looteria.repository.ListingPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ListingAdminService {

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Cloudinary cloudinary;

    /**
     * Obtener todas las publicaciones 
     */
    public List<ListingDetailDTO> getAllListingsForAdmin() {
        List<ListingPost> listings = listingPostRepository.findAll();
        List<ListingDetailDTO> results = new ArrayList<>();
        for (ListingPost listing : listings) {
            results.add(convertToDTO(listing));
        }
        return results;
    }

    /**
     * Obtener publicaciones de un usuario específico
     */
    public List<ListingDetailDTO> getListingsByUserId(Long userId) {
        List<ListingPost> listings = listingPostRepository.findAll();
        List<ListingDetailDTO> results = new ArrayList<>();
        for (ListingPost listing : listings) {
            if (listing.getUsuario() != null && listing.getUsuario().getIdUsuario().equals(userId)) {
                results.add(convertToDTO(listing));
            }
        }
        return results;
    }

    /**
     * Eliminar publicación por ID
     */
    public void deleteListing(Long id) throws IOException {
        ListingPost listing = listingPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        
        // Eliminar imágenes de Cloudinary
        List<Image> images = (List<Image>) imageRepository.findByPublicacion_IdPublicacion(id);
        for (Image image : images) {
            if (image.getPublicId() != null) {
                cloudinary.uploader().destroy(image.getPublicId(), ObjectUtils.emptyMap());
            }
        }
        
        // Eliminar imágenes de la base de datos
        imageRepository.deleteAll(images);
        
        // Eliminar publicación
        listingPostRepository.delete(listing);
    }

    /**
     * Obtener publicación por ID como DTO
     */
    public ListingDetailDTO getListingById(Long id) {
        return listingPostRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Convertir entidad ListingPost a DTO
     */
    private ListingDetailDTO convertToDTO(ListingPost listing) {
        ListingDetailDTO dto = new ListingDetailDTO();
        
        try {
            dto.setIdPublicacion(listing.getIdPublicacion());
            
            if (listing.getUsuario() != null) {
                dto.setIdUsuario(listing.getUsuario().getIdUsuario());
                dto.setNombreUsuario(listing.getUsuario().getNombreUsuario());
                dto.setEmail(listing.getUsuario().getEmail());
            } else {
                dto.setIdUsuario(null);
                dto.setNombreUsuario("Desconocido");
                dto.setEmail("Desconocido");
            }
            
            if (listing.getProducto() != null) {
                dto.setTitulo(listing.getProducto().getTitulo());
                dto.setDescripcion(listing.getProducto().getDescripcion());
                dto.setPlataforma(listing.getProducto().getPlataforma() != null ? 
                        listing.getProducto().getPlataforma().getNombre() : "Desconocida");
                dto.setTipoArticulo(listing.getProducto().getTipoArticulo() != null ? 
                        listing.getProducto().getTipoArticulo().getNombre() : "No especificado");
                dto.setFechaLanzamiento(listing.getProducto().getFechaLanzamiento());
            } else {
                dto.setTitulo("Desconocido");
                dto.setDescripcion("");
                dto.setPlataforma("Desconocida");
                dto.setTipoArticulo("No especificado");
            }
            
            if (listing.getTipoTransaccion() != null) {
                dto.setTipoTransaccion(listing.getTipoTransaccion().toString());
            } else {
                dto.setTipoTransaccion("DESCONOCIDO");
            }
            
            dto.setPrecio(listing.getPrecio());
            
            if (listing.getEstadoArticulo() != null) {
                dto.setEstadoArticulo(listing.getEstadoArticulo().getNombre());
            } else {
                dto.setEstadoArticulo("No especificado");
            }
            
            dto.setDescripcionEstado(listing.getDescripcionEstado());
            
            if (listing.getIdioma() != null) {
                dto.setIdioma(listing.getIdioma().getNombre());
            } else {
                dto.setIdioma("Desconocido");
            }
            
            if (listing.getRegion() != null) {
                dto.setRegion(listing.getRegion().getNombre());
            } else {
                dto.setRegion("Desconocida");
            }
            
            dto.setFechaCreacion(listing.getFechaCreacion());
            
            if (listing.getEstadoPublicacion() != null) {
                dto.setEstadoPublicacion(listing.getEstadoPublicacion().toString());
            } else {
                dto.setEstadoPublicacion("DESCONOCIDO");
            }

            dto.setDestacado(listing.getDestacado() != null ? listing.getDestacado() : false);

            // Poblar imágenes
            List<Image> images = (List<Image>) imageRepository.findByPublicacion_IdPublicacion(listing.getIdPublicacion());
            List<String> imageUrls = images.stream()
                    .map(Image::getRutaImagen)
                    .collect(Collectors.toList());
            dto.setImagenes(imageUrls);
        } catch (Exception e) {
            // Log silenciosamente si hay errores
            e.printStackTrace();
        }

        return dto;
    }
}
