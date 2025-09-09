package com.example.demo.controllers;


import com.example.demo.entity.Order;
import com.example.demo.entity.*;
import com.example.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{id}")
    public List<Order> userOrders(@PathVariable("id") Long userId) {
        return orderService.getOrdersForUser(userId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> allOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestParam String status,
                              @RequestParam String tracking) {
        return orderService.updateStatus(orderId, status, tracking);
    }
}

