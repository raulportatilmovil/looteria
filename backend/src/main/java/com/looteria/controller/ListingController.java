package com.looteria.controller;

import com.looteria.dto.SearchListingDTO;
import com.looteria.dto.ReviewDTO;
import com.looteria.dto.CreateListingRequestDTO;
import com.looteria.dto.CreateReviewRequestDTO;
import com.looteria.dto.UpdateListingRequestDTO;
import com.looteria.entity.ListingPost;
import com.looteria.service.ListingPostService;
import com.looteria.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicaciones")
public class ListingController {

    @Autowired
    private ListingPostService listingPostService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/activas")
    public ResponseEntity<List<SearchListingDTO>> getActiveListings() {
        List<SearchListingDTO> listings = listingPostService.getActiveListings();
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/{id}")
public ResponseEntity<?> getListingById(@PathVariable Long id) {
    Optional<com.looteria.dto.ListingDetailDTO> listing = listingPostService.getListingDetailById(id);
    if (listing.isPresent()) {
        return ResponseEntity.ok(listing.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Publicación no encontrada"));
}

    @PostMapping
    public ResponseEntity<?> createListing(@RequestBody CreateListingRequestDTO request) {
        try {
            ListingPost listing = listingPostService.createListing(
                    request.getUserId(),
                    request.getTitulo(),
                    request.getDescripcion(),
                    request.getPlataforma(),
                    request.getTipoArticulo(),
                    request.getTipoTransaccion(),
                    request.getPrecio(),
                    request.getEstado(),
                    request.getDescripcionEstado(),
                    request.getEspecificaciones(),
                    request.getIdioma(),
                    request.getRegion());
            return ResponseEntity.status(HttpStatus.CREATED).body(listing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateListing(@PathVariable Long id, @RequestBody UpdateListingRequestDTO request) {
        try {
            ListingPost listing = listingPostService.updateListing(
                    id,
                    request.getTitulo(),
                    request.getDescripcionEstado(),
                    request.getPrecio(),
                    request.getTipoTransaccion());
            return ResponseEntity.ok(listing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable Long id) {
        try {
            listingPostService.deleteListing(id);
            return ResponseEntity.ok(new SuccessResponse("Publicación eliminada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}/resenas")
    public ResponseEntity<List<ReviewDTO>> getListingReviews(@PathVariable Long id) {
        List<ReviewDTO> reviews = reviewService.getReviewsByListing(id);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{id}/resenas")
    public ResponseEntity<?> addReview(@PathVariable Long id, @RequestBody CreateReviewRequestDTO request) {
        try {
            reviewService.createListingReview(
                    id,
                    request.getAutorId(),
                    request.getPuntuacion(),
                    request.getComentario());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse("Reseña creada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }

    static class SuccessResponse {
        public String message;

        public SuccessResponse(String message) {
            this.message = message;
        }
    }
}
