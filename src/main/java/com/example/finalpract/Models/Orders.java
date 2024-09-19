package com.example.finalpract.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int Number_Orders;
    private int Prise;
    @JsonManagedReference
    @OneToMany(mappedBy = "ordersFK", fetch = FetchType.EAGER)
    private Collection<TVs> TVsFK;

    @OneToOne
    @JoinColumn(name = "couriersFK", referencedColumnName = "id")
    private Couriers couriersFK;

    public Orders(Long id, int number_Orders, int prise, Collection<TVs> TVsFK, Couriers couriersFK) {
        this.id = id;
        Number_Orders = number_Orders;
        Prise = prise;
        this.TVsFK = TVsFK;
        this.couriersFK = couriersFK;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber_Orders() {
        return Number_Orders;
    }

    public void setNumber_Orders(int number_Orders) {
        Number_Orders = number_Orders;
    }

    public int getPrise() {
        return Prise;
    }

    public void setPrise(int prise) {
        Prise = prise;
    }

    public Collection<TVs> getTVsFK() {
        return TVsFK;
    }

    public void setTVsFK(Collection<TVs> TVsFK) {
        this.TVsFK = TVsFK;
    }

    public Couriers getCouriersFK() {
        return couriersFK;
    }

    public void setCouriersFK(Couriers couriersFK) {
        this.couriersFK = couriersFK;
    }
}
