package com.looteria.service;

import com.looteria.dto.HomePageListingDTO;
import com.looteria.entity.Image;
import com.looteria.entity.ListingPost;
import com.looteria.repository.ImageRepository;
import com.looteria.repository.ListingPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HomePageService {

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<HomePageListingDTO> getFeaturedListings() {
        return StreamSupport.stream(
                listingPostRepository.findByDestacadoTrueAndEstadoPublicacion(ListingPost.PublicationStatus.ACTIVA).spliterator(),
                false
        ).map(this::mapToDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<HomePageListingDTO> getRecentListings(int limit) {
        List<ListingPost> listings = listingPostRepository.findByEstadoPublicacionOrderByFechaCreacionDesc(ListingPost.PublicationStatus.ACTIVA);
        return listings.stream()
                .limit(limit)
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HomePageListingDTO> getPopularListings(int limit) {
        List<ListingPost> listings = listingPostRepository.findPopularByStatus(ListingPost.PublicationStatus.ACTIVA);
        return listings.stream()
                .limit(limit)
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public void toggleFeatured(Long listingId) {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        listing.setDestacado(!listing.getDestacado());
        listingPostRepository.save(listing);
    }

    private HomePageListingDTO mapToDTO(ListingPost listing) {
        HomePageListingDTO dto = new HomePageListingDTO();
        dto.setIdPublicacion(listing.getIdPublicacion());
        dto.setIdProducto(listing.getProducto() != null ? listing.getProducto().getIdProducto() : null);
        dto.setTitulo(listing.getProducto() != null ? listing.getProducto().getTitulo() : "");
        dto.setDescripcion(listing.getProducto() != null ? listing.getProducto().getDescripcion() : "");
        dto.setPlataformaNombre(listing.getProducto() != null && listing.getProducto().getPlataforma() != null ? listing.getProducto().getPlataforma().getNombre() : "");
        dto.setTipoArticuloNombre(listing.getProducto() != null && listing.getProducto().getTipoArticulo() != null ? listing.getProducto().getTipoArticulo().getNombre() : "");
        dto.setPrecio(listing.getPrecio());
        dto.setEstadoArticuloNombre(listing.getEstadoArticulo() != null ? listing.getEstadoArticulo().getNombre() : "");
        dto.setTipoTransaccion(listing.getTipoTransaccion().name());
        dto.setRegionNombre(listing.getRegion() != null ? listing.getRegion().getNombre() : "");
        dto.setNombreUsuario(listing.getUsuario() != null ? listing.getUsuario().getNombreUsuario() : "");
        dto.setDescripcionEstado(listing.getDescripcionEstado());
        dto.setIdiomaNombre(listing.getIdioma() != null ? listing.getIdioma().getNombre() : "");
        dto.setFechaCreacion(listing.getFechaCreacion());
        dto.setDestacado(listing.getDestacado());

        // Load images
        List<Image> images = (List<Image>) imageRepository.findByPublicacion_IdPublicacion(listing.getIdPublicacion());
        List<String> imageUrls = images.stream()
                .map(Image::getRutaImagen)
                .collect(Collectors.toList());
        dto.setImagenes(imageUrls);

        return dto;
    }
}
