package com.looteria.service;

import com.looteria.dto.ListingDetailDTO;
import com.looteria.dto.ListingSearchProjection;
import com.looteria.dto.SearchListingDTO;
import com.looteria.entity.*;
import com.looteria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Service
public class ListingPostService {

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    // ─── Listado activo ──────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<SearchListingDTO> getActiveListings() {
        List<SearchListingDTO> results = new ArrayList<>();
        for (ListingSearchProjection p : listingPostRepository.findAllActiveListings()) {
            SearchListingDTO dto = mapToSearchDTO(p);
            List<Image> imgs = StreamSupport.stream(
                    imageRepository.findByPublicacion_IdPublicacion(p.getIdPublicacion()).spliterator(), false)
                    .collect(Collectors.toList());
            if (!imgs.isEmpty()) {
                dto.setImagenUrl(imgs.get(0).getRutaImagen());
            }
            results.add(dto);
        }
        return results;
    }

    // ─── Detalle de publicación ──────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public Optional<ListingDetailDTO> getListingDetailById(Long id) {
        return listingPostRepository.findById(id).map(listing -> {
            ListingDetailDTO dto = new ListingDetailDTO();

            // Publicación
            dto.setIdPublicacion(listing.getIdPublicacion());
            dto.setTipoTransaccion(listing.getTipoTransaccion() != null
                    ? listing.getTipoTransaccion().name() : null);
            dto.setPrecio(listing.getPrecio());
            dto.setDescripcionEstado(listing.getDescripcionEstado());
            dto.setFechaCreacion(listing.getFechaCreacion());
            dto.setEstadoPublicacion(listing.getEstadoPublicacion() != null
                    ? listing.getEstadoPublicacion().name() : null);

            // Categorías
            if (listing.getEstadoArticulo() != null) {
                String nombreEstado = listing.getEstadoArticulo().getNombre();
                dto.setEstadoArticulo(nombreEstado);
                dto.setEstado(nombreEstado); 
            }
            if (listing.getRegion() != null)
                dto.setRegion(listing.getRegion().getNombre());
            if (listing.getIdioma() != null)
                dto.setIdioma(listing.getIdioma().getNombre());

            // Campos directos de publicación
            dto.setTitulo(listing.getTitulo());
            dto.setProducto(listing.getTitulo());
            dto.setDescripcion(listing.getDescripcion());
            dto.setEspecificaciones(listing.getDescripcion());
            if (listing.getPlataforma() != null)
                dto.setPlataforma(listing.getPlataforma().getNombre());
            if (listing.getTipoArticulo() != null)
                dto.setTipoArticulo(listing.getTipoArticulo().getNombre());

            // Usuario
            if (listing.getUsuario() != null) {
                User u = listing.getUsuario();
                dto.setIdUsuario(u.getIdUsuario());
                dto.setUsuarioId(u.getIdUsuario());      
                dto.setNombreUsuario(u.getNombreUsuario());
                dto.setUsuarioNombre(u.getNombreUsuario()); 
                dto.setEmail(u.getEmail());
                dto.setUsuarioEmail(u.getEmail());       
                dto.setUsuarioUbicacion(u.getUbicacion());
                dto.setUsuarioReputacion(u.getReputacionMedia());
                dto.setUsuarioVentas(0L); 
            }

            // Imágenes
            List<String> urls = StreamSupport
                    .stream(imageRepository.findByPublicacion_IdPublicacion(
                            listing.getIdPublicacion()).spliterator(), false)
                    .map(Image::getRutaImagen)
                    .collect(Collectors.toList());
            dto.setImagenes(urls);

            return dto;
        });
    }

    // ─── Crear publicación ───────────────────────────────────────────────────────

    @Transactional
    public ListingPost createListing(Long userId, String titulo, String descripcion,
            String plataforma, String tipoArticulo, String tipoTransaccion, BigDecimal precio,
            String estado, String descripcionEstado, String especificaciones,
            String idioma, String region) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ListingPost listing = new ListingPost();
        listing.setUsuario(user);
        listing.setTitulo(titulo);
        listing.setDescripcion(descripcion);
        listing.setTipoTransaccion(ListingPost.TransactionType.valueOf(tipoTransaccion));
        listing.setPrecio(precio);
        listing.setDescripcionEstado(descripcionEstado);
        listing.setFechaCreacion(LocalDateTime.now());
        listing.setEstadoPublicacion(ListingPost.PublicationStatus.ACTIVA);

        if (plataforma != null && !plataforma.isEmpty()) {
            categoryRepository.findFirstByNombreAndTipo(plataforma, "PLATAFORMA")
                    .ifPresent(listing::setPlataforma);
        }
        if (tipoArticulo != null && !tipoArticulo.isEmpty()) {
            categoryRepository.findFirstByNombreAndTipo(tipoArticulo, "TIPO_ARTICULO")
                    .ifPresent(listing::setTipoArticulo);
        }
        if (estado != null && !estado.isEmpty()) {
            categoryRepository.findFirstByNombreAndTipo(estado, "ESTADO_ARTICULO")
                    .ifPresent(listing::setEstadoArticulo);
        }
        if (idioma != null && !idioma.isEmpty()) {
            categoryRepository.findFirstByNombreAndTipo(idioma, "IDIOMA")
                    .ifPresent(listing::setIdioma);
        }
        if (region != null && !region.isEmpty()) {
            categoryRepository.findFirstByNombreAndTipo(region, "REGION")
                    .ifPresent(listing::setRegion);
        }

        return listingPostRepository.save(listing);
    }

    // ─── Actualizar publicación ──────────────────────────────────────────────────

    @Transactional
    public ListingPost updateListing(Long listingId, String titulo, String descripcionEstado,
                                     BigDecimal precio, String tipoTransaccion) {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        if (titulo != null && !titulo.isEmpty()) {
            listing.setTitulo(titulo);
        }
        if (descripcionEstado != null) {
            listing.setDescripcionEstado(descripcionEstado);
        }
        if (precio != null) {
            listing.setPrecio(precio);
        }
        if (tipoTransaccion != null) {
            listing.setTipoTransaccion(ListingPost.TransactionType.valueOf(tipoTransaccion));
        }

        return listingPostRepository.save(listing);
    }

    // ─── Eliminar publicación ────────────────────────────────────────────────────

    @Transactional
    public void deleteListing(Long listingId) {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        listingPostRepository.delete(listing);
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────────

    private SearchListingDTO mapToSearchDTO(ListingSearchProjection p) {
        SearchListingDTO dto = new SearchListingDTO();
        dto.setIdPublicacion(p.getIdPublicacion());
        dto.setTitulo(p.getTitulo());
        dto.setDescripcion(p.getDescripcion());
        dto.setPlataforma(p.getPlataformaNombre()     != null ? p.getPlataformaNombre()     : "Desconocida");
        dto.setTipoArticulo(p.getTipoArticuloNombre() != null ? p.getTipoArticuloNombre()   : "Desconocido");
        dto.setPrecio(p.getPrecio());
        dto.setEstadoArticulo(p.getEstadoArticuloNombre() != null ? p.getEstadoArticuloNombre() : "Desconocido");
        dto.setTipoTransaccion(p.getTipoTransaccion() != null ? p.getTipoTransaccion()      : "VENTA");
        dto.setRegion(p.getRegionNombre()             != null ? p.getRegionNombre()          : "No especificado");
        dto.setUsuario(p.getNombreUsuario()           != null ? p.getNombreUsuario()         : "Anónimo");
        dto.setDescripcionEstado(p.getDescripcionEstado());
        dto.setIdioma(p.getIdiomaNombre()             != null ? p.getIdiomaNombre()          : "No especificado");
        dto.setFechaCreacion(p.getFechaCreacion());
        return dto;
    }
}