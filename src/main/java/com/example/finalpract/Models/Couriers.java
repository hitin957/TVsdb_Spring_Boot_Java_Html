package com.example.finalpract.Models;

import jakarta.persistence.*;

@Entity
public class Couriers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staffsFK", referencedColumnName = "id")
    private Staffs staffsFK;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportsFK", referencedColumnName = "id")
    private Transports transportsFK;



    public Couriers(Long id, Staffs staffsFK, Transports transportsFK) {
        this.id = id;
        this.staffsFK = staffsFK;
        this.transportsFK = transportsFK;
    }

    public Couriers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staffs getStaffsFK() {
        return staffsFK;
    }

    public void setStaffsFK(Staffs staffsFK) {
        this.staffsFK = staffsFK;
    }

    public Transports getTransportsFK() {
        return transportsFK;
    }

    public void setTransportsFK(Transports transportsFK) {
        this.transportsFK = transportsFK;
    }
}
