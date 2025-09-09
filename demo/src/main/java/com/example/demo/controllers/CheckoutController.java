package com.example.demo.controllers;

import com.example.demo.Services.OfferService;
import com.example.demo.Services.OrderService;
import com.example.demo.Services.PaymentService;
import com.example.demo.Services.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Autowired private OrderService orderService;
    @Autowired private PaymentService paymentService;
    @Autowired private OfferService offerService;
    @Autowired private UserService userService;

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody Map<String,Object> payload,
                                      @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        if (authUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status","error","message","Unauthorized"));
        }

        Optional<User> maybeUser = userService.findByUsername(authUser.getUsername());
        if (maybeUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status","error","message","User not found"));
        }
        User user = maybeUser.get();

        // TODO: parse payload items → create order, apply offerCode, create payment, etc.
        return ResponseEntity.ok(Map.of(
                "status","success",
                "message","Order placed (mock). Implement DTO mapping in code."
        ));
    }
}
