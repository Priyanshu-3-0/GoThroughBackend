package com.example.demo.controllers;

import com.example.demo.entity.Offer;
import com.example.demo.Services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public Offer create(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<?> validate(@PathVariable String code) {
        Optional<Offer> opt = offerService.validateOffer(code);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(400).body("Invalid/Expired offer");
    }
}

