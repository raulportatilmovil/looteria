package com.looteria.controller;

import com.looteria.dto.HomePageListingDTO;
import com.looteria.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/featured")
    public ResponseEntity<List<HomePageListingDTO>> getFeaturedListings() {
        List<HomePageListingDTO> listings = homePageService.getFeaturedListings();
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<HomePageListingDTO>> getRecentListings(@RequestParam(defaultValue = "6") int limit) {
        List<HomePageListingDTO> listings = homePageService.getRecentListings(limit);
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<HomePageListingDTO>> getPopularListings(@RequestParam(defaultValue = "4") int limit) {
        List<HomePageListingDTO> listings = homePageService.getPopularListings(limit);
        return ResponseEntity.ok(listings);
    }

    @PostMapping("/listings/{id}/toggle-featured")
    public ResponseEntity<Void> toggleFeatured(@PathVariable Long id) {
        homePageService.toggleFeatured(id);
        return ResponseEntity.ok().build();
    }
}
