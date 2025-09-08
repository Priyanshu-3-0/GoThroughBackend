package Controllers;

import entity.*;
import Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Autowired private OrderService orderService;
    @Autowired private PaymentService paymentService;
    @Autowired private OfferService offerService;
    @Autowired private UserService userService;

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody Map<String,Object> payload, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        // payload contains: items (list), offerCode (optional)
        // For simplicity, map request manually
        // In real project create DTOs

        // 1) validate user
        User user = userService.findByUsername(authUser.getUsername()).orElseThrow();

        // 2) build order (assuming frontend sends items with dish id and qty)
        // pseudo: convert items → OrderItem objects, set price snapshot
        // 3) apply offer if present
        // 4) save order, create payment (mock)
        // Return order + payment status

        // NOTE: For brevity I'm returning a mock success response. Implement full deserialization as needed.
        return ResponseEntity.ok(Map.of("status","success","message","Order placed (mock). Implement DTO mapping in code.)"));
    }
}
