package com.example.demo.controllers;

import com.example.demo.entity.Dish;
import com.example.demo.Services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired private DishService dishService;

    @GetMapping
    public List<Dish> available() { return dishService.getAvailableDishes(); }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Dish> all() { return dishService.getAll(); }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Dish add(@RequestBody Dish dish){ return dishService.save(dish); }

    @PutMapping("/{id}/availability")
    @PreAuthorize("hasRole('ADMIN')")
    public Dish setAvailability(@PathVariable Long id, @RequestParam boolean available){
        Dish d = dishService.getAll().stream().filter(x->x.getId().equals(id)).findFirst().orElseThrow();
        d.setAvailability(available);
        return dishService.save(d);
    }
}
