package com.looteria.service;

import com.looteria.dto.ReviewDTO;
import com.looteria.entity.ListingPost;
import com.looteria.entity.Review;
import com.looteria.entity.User;
import com.looteria.repository.ListingPostRepository;
import com.looteria.repository.ReviewRepository;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;
    
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByListing(Long listingId) {
        Set<Review> allReviews = new LinkedHashSet<>();
        allReviews.addAll(reviewRepository.findByPublicacion_IdPublicacion(listingId));
        allReviews.addAll(reviewRepository.findByTransaccionPublicacionId(listingId));
        List<ReviewDTO> result = new ArrayList<>();
        for (Review review : allReviews) {
            result.add(mapToDTO(review));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> getReceivedReviews(Long userId) {
        Iterable<Review> reviews = reviewRepository.findByReceptor_IdUsuario(userId);
        List<ReviewDTO> result = new ArrayList<>();
        for (Review review : reviews) {
            result.add(mapToDTO(review));
        }
        return result;
    }

    @Transactional
    public Review createListingReview(Long listingId, Long autorId, Integer puntuacion, String comentario) {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        User autor = userRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Usuario autor no encontrado"));

        User receptor = listing.getUsuario();
        if (receptor == null) {
            throw new RuntimeException("La publicación no tiene propietario");
        }

        Review review = new Review();
        review.setPublicacion(listing);
        review.setAutor(autor);
        review.setReceptor(receptor);
        review.setPuntuacion(puntuacion);
        review.setComentario(comentario);
        review.setFechaCreacion(LocalDateTime.now());

        Review saved = reviewRepository.save(review);
        updateReputacionMedia(receptor.getIdUsuario());
        if (puntuacion >= 4) {
            receptor.setPuntosAcumulados(receptor.getPuntosAcumulados() + 25L);
            userRepository.save(receptor);
        }
        return saved;
    }

    @Transactional
    public void updateReputacionMedia(Long userId) {
        Iterable<Review> reviews = reviewRepository.findByReceptor_IdUsuario(userId);
        int count = 0;
        int sum = 0;
        for (Review r : reviews) {
            sum += r.getPuntuacion();
            count++;
        }
        BigDecimal avg = count > 0 ? BigDecimal.valueOf((double) sum / count).setScale(2, java.math.RoundingMode.HALF_UP) : BigDecimal.ZERO;
        userRepository.findById(userId).ifPresent(u -> {
            u.setReputacionMedia(avg);
            userRepository.save(u);
        });
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        
        Long receptorId = review.getReceptor().getIdUsuario();
        reviewRepository.deleteById(reviewId);
        updateReputacionMedia(receptorId);
    }

    private ReviewDTO mapToDTO(Review review) {
        return new ReviewDTO(
                review.getIdResena(),
                review.getAutor().getNombreUsuario(),
                review.getPuntuacion(),
                review.getComentario(),
                review.getFechaCreacion().toString()
        );
    }
}
