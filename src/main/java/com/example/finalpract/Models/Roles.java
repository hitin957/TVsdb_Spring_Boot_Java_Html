package com.example.finalpract.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 30,min = 5, message = "Максимальное колличество символов 30 и минимальное колличество символов 5")
    private String Name_role;
    @JsonManagedReference
    @OneToMany(mappedBy = "RolesFK", fetch = FetchType.EAGER)
    private Collection<Staffs> StaffsFK;

    public Roles(Long id, String name_role, Collection<Staffs> staffsFK) {
        this.id = id;
        Name_role = name_role;
        StaffsFK = staffsFK;
    }

    public Roles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_role() {
        return Name_role;
    }

    public void setName_role(String name_role) {
        Name_role = name_role;
    }

    public Collection<Staffs> getStaffsFK() {
        return StaffsFK;
    }

    public void setStaffsFK(Collection<Staffs> staffsFK) {
        StaffsFK = staffsFK;
    }
}
