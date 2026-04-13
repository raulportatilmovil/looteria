package com.looteria.controller;

import com.looteria.dto.SearchListingDTO;
import com.looteria.service.ListingPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(origins = "*")
public class ListingController {
    
    @Autowired 
    private ListingPostService listingPostService;
    
    @GetMapping("/activas")
    public ResponseEntity<List<SearchListingDTO>> getActiveListings() {
        List<SearchListingDTO> listings = listingPostService.getActiveListings();
        return ResponseEntity.ok(listings);
    }
}
