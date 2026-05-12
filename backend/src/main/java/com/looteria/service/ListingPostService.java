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
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    // ─── Listado activo ──────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<SearchListingDTO> getActiveListings() {
        List<SearchListingDTO> results = new ArrayList<>();
        for (ListingSearchProjection p : listingPostRepository.findAllActiveListings()) {
            results.add(mapToSearchDTO(p));
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
            dto.setEnvio(listing.getEnvio());

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

            // Producto
            if (listing.getProducto() != null) {
                Product p = listing.getProducto();
                dto.setTitulo(p.getTitulo());
                dto.setProducto(p.getTitulo());          
                dto.setDescripcion(p.getDescripcion());
                dto.setEspecificaciones(p.getDescripcion());

                if (p.getPlataforma() != null)
                    dto.setPlataforma(p.getPlataforma().getNombre());
                if (p.getTipoArticulo() != null)
                    dto.setTipoArticulo(p.getTipoArticulo().getNombre());
                dto.setFechaLanzamiento(p.getFechaLanzamiento());
            }

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
    public ListingPost createListing(Long userId, String producto, String descripcion,
            String plataforma, String tipoTransaccion, BigDecimal precio,
            String estado, String descripcionEstado, String especificaciones,
            String idioma, String region) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Product product = productRepository
                .findByTituloContainingIgnoreCase(producto)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Product newProduct = new Product();
                    newProduct.setTitulo(producto);
                    newProduct.setDescripcion(descripcion);
                    if (plataforma != null && !plataforma.isEmpty()) {
                        categoryRepository.findFirstByNombreAndTipo(plataforma, "PLATAFORMA")
                                .ifPresent(newProduct::setPlataforma);
                    }
                    return productRepository.save(newProduct);
                });

        ListingPost listing = new ListingPost();
        listing.setUsuario(user);
        listing.setProducto(product);
        listing.setTipoTransaccion(ListingPost.TransactionType.valueOf(tipoTransaccion));
        listing.setPrecio(precio);
        listing.setDescripcionEstado(descripcionEstado);
        listing.setFechaCreacion(LocalDateTime.now());
        listing.setEstadoPublicacion(ListingPost.PublicationStatus.ACTIVA);
        listing.setEnvio(false);

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
    public ListingPost updateListing(Long listingId, String descripcionEstado, 
                                     BigDecimal precio, String tipoTransaccion, Boolean envio) {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        if (descripcionEstado != null) {
            listing.setDescripcionEstado(descripcionEstado);
        }
        if (precio != null) {
            listing.setPrecio(precio);
        }
        if (tipoTransaccion != null) {
            listing.setTipoTransaccion(ListingPost.TransactionType.valueOf(tipoTransaccion));
        }
        if (envio != null) {
            listing.setEnvio(envio);
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
        return new SearchListingDTO(
                p.getIdPublicacion(),
                p.getIdProducto(),
                p.getTitulo(),
                p.getDescripcion(),
                p.getPlataformaNombre()      != null ? p.getPlataformaNombre()      : "Desconocida",
                p.getTipoArticuloNombre()    != null ? p.getTipoArticuloNombre()    : "Desconocido",
                p.getPrecio(),
                p.getEstadoArticuloNombre()  != null ? p.getEstadoArticuloNombre()  : "Desconocido",
                p.getTipoTransaccion()       != null ? p.getTipoTransaccion()       : "VENTA",
                p.getRegionNombre()          != null ? p.getRegionNombre()          : "No especificado",
                p.getNombreUsuario()         != null ? p.getNombreUsuario()         : "Anónimo",
                p.getDescripcionEstado(),
                p.getIdiomaNombre()          != null ? p.getIdiomaNombre()          : "No especificado",
                p.getFechaCreacion());
    }
}