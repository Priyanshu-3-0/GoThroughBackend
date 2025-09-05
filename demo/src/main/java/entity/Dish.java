package entity;

import jakarta.persistence.*;

@Entity
public class Dish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private boolean availability = true;

    private String category;

}
