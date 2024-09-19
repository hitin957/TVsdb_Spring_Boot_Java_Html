package com.example.finalpract.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 30, min = 5, message = "Максимальное колличество символов 30 и минимальное колличество символов 5")
    private String Name_Departments;
    @JsonManagedReference
    @OneToMany(mappedBy = "DepartmentsFK", fetch = FetchType.EAGER)
    private Collection<Staffs> StaffFK;

    public Departments(Long id, String name_Departments, Collection<Staffs> staffFK) {
        this.id = id;
        Name_Departments = name_Departments;
        StaffFK = staffFK;
    }

    public Departments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_Departments() {
        return Name_Departments;
    }

    public void setName_Departments(String name_Departments) {
        Name_Departments = name_Departments;
    }

    public Collection<Staffs> getStaffFK() {
        return StaffFK;
    }

    public void setStaffFK(Collection<Staffs> staffFK) {
        StaffFK = staffFK;
    }
}
