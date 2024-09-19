package com.example.finalpract.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class TVs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, min = 5, message = "Название - Максимальное колличество символов 50 и минимальное колличество символов 5")
    private String Name_TV;
    @Size(max = 200, min = 10,message = "Описание - Максимальное колличество символов 100 и минимальное колличество символов 10")
    private String Description_TV;
    @Min(value = 20000, message = "Минимальная цена телевизора 20000 рублей")
    @Max(value = 100000, message = "Максимальная цена телевизора 100000 рублей")
    private int Price_TV;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders ordersFK;

    public TVs(Long id, String name_TV, String description_TV, int price_TV, Orders ordersFK) {
        this.id = id;
        Name_TV = name_TV;
        Description_TV = description_TV;
        Price_TV = price_TV;
        this.ordersFK = ordersFK;
    }

    public TVs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_TV() {
        return Name_TV;
    }

    public void setName_TV(String name_TV) {
        Name_TV = name_TV;
    }

    public String getDescription_TV() {
        return Description_TV;
    }

    public void setDescription_TV(String description_TV) {
        Description_TV = description_TV;
    }

    public int getPrice_TV() {
        return Price_TV;
    }

    public void setPrice_TV(int price_TV) {
        Price_TV = price_TV;
    }

    public Orders getOrdersFK() {
        return ordersFK;
    }

    public void setOrdersFK(Orders ordersFK) {
        this.ordersFK = ordersFK;
    }
}
