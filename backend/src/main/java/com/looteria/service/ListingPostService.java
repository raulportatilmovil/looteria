package com.looteria.service;

import com.looteria.dto.ListingSearchProjection;
import com.looteria.dto.SearchListingDTO;
import com.looteria.repository.ListingPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListingPostService {
    
    @Autowired
    private ListingPostRepository listingPostRepository;
    
    @Transactional(readOnly = true)
    public List<SearchListingDTO> getActiveListings() {
        List<SearchListingDTO> results = new ArrayList<>();
        Iterable<ListingSearchProjection> activeListings = listingPostRepository.findAllActiveListings();
        
        for (ListingSearchProjection projection : activeListings) {
            SearchListingDTO dto = mapToSearchDTO(projection);
            results.add(dto);
        }
        
        return results;
    }
    
    private SearchListingDTO mapToSearchDTO(ListingSearchProjection projection) {
        return new SearchListingDTO(
            projection.getIdPublicacion(),
            projection.getIdProducto(),
            projection.getTitulo(),
            projection.getDescripcion(),
            projection.getPlataformaNombre() != null ? projection.getPlataformaNombre() : "Desconocida",
            projection.getTipoArticuloNombre() != null ? projection.getTipoArticuloNombre() : "Desconocido",
            projection.getFranquiciaNombre(),
            projection.getPrecio(),
            projection.getEstadoArticuloNombre() != null ? projection.getEstadoArticuloNombre() : "Desconocido",
            projection.getTipoTransaccion() != null ? projection.getTipoTransaccion() : "VENTA",
            projection.getRegionNombre() != null ? projection.getRegionNombre() : "No especificado",
            projection.getNombreUsuario() != null ? projection.getNombreUsuario() : "Anónimo",
            projection.getDescripcionEstado(),
            projection.getIdiomaNombre() != null ? projection.getIdiomaNombre() : "No especificado",
            projection.getFechaCreacion()
        );
    }
}
