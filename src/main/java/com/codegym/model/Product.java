package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "Please fill out from 2 to 30 characters ! ")
    private String name;

    @Min(value = 5, message = "Please fill out minimum 5$")
    private double price;

    @ManyToOne
    private Category category;

    @NotEmpty(message = "Please fill out description")
    private String description;

    private Date datetime;

    @Min(value = 1, message = "Please fill out minimum 1 quantity")
    private int quantity;

    public Product(Long id, @Size(min = 2, max = 30, message = "Please fill out from 2 to 30 characters ! ") String name, @Min(value = 5, message = "Please fill out minimum 5$") double price, Category category, @NotEmpty(message = "Please fill out description") String description, Date datetime, @Min(value = 1, message = "Please fill out minimum 1 quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.datetime = datetime;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(@Size(min = 2, max = 30, message = "Please fill out from 2 to 30 characters ! ") String name, @Min(value = 5, message = "Please fill out minimum 5$") double price, Category category, @NotEmpty(message = "Please fill out description") String description, Date datetime, @Min(value = 1, message = "Please fill out minimum 1 quantity") int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.datetime = datetime;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
