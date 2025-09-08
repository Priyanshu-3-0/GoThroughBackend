package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private boolean isMember = false;
    private LocalDateTime membershipExpiry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isMember() { return isMember; }
    public void setMember(boolean member) { isMember = member; }

    public LocalDateTime getMembershipExpiry() { return membershipExpiry; }
    public void setMembershipExpiry(LocalDateTime membershipExpiry) { this.membershipExpiry = membershipExpiry; }
}
