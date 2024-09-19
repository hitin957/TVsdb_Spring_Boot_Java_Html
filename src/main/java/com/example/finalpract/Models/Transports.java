package com.example.finalpract.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Transports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, min = 1,message = "Максимальное колличество символов 50 и минимальное колличество символов 1")
    private String Name_transport;
    @Size(max = 6, min = 6, message = "Максимальное и минимальное колличество символов 6")
    private String Number_transport;

    public Transports(Long id, String name_transport, String number_transport) {
        this.id = id;
        Name_transport = name_transport;
        Number_transport = number_transport;
    }

    public Transports() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_transport() {
        return Name_transport;
    }

    public void setName_transport(String name_transport) {
        Name_transport = name_transport;
    }

    public String getNumber_transport() {
        return Number_transport;
    }

    public void setNumber_transport(String number_transport) {
        Number_transport = number_transport;
    }
}
