package com.infinitivus.project.spring_boot_security_base.entity.security;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String role;

    public UserRole() {
    }

    public UserRole(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserRole(Integer id) {
        this.id = id;
    }

    public UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

