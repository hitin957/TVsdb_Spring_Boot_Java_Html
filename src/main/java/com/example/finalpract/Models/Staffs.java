package com.example.finalpract.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Staffs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100, min = 10 ,message = "Максимальное колличество символов 100 и минимальное колличество символов 10")
    private String FIO;
    private LocalDate date;
    @Max(value = 40, message = "Максимальное колличество 40")
    private int Work_experience;
    private String Login;
    @Size(min = 8, message = "Минимальное колличество символов 8")
    private String password;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Roles RolesFK;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Departments DepartmentsFK;

    public Staffs(String FIO, LocalDate date_of_birth, int work_experience, String login, String password, Roles rolesFK, Departments departmentsFK) {
        this.FIO = FIO;
        date = date_of_birth;
        Work_experience = work_experience;
        Login = login;
        this.password = password;
        RolesFK = rolesFK;
        DepartmentsFK = departmentsFK;
    }

    public Staffs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public LocalDate getDate_of_birth() {
        return date;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        date = date_of_birth;
    }

    public int getWork_experience() {
        return Work_experience;
    }

    public void setWork_experience(int work_experience) {
        Work_experience = work_experience;
    }

    public Roles getRolesFK() {
        return RolesFK;
    }

    public void setRolesFK(Roles rolesFK) {
        RolesFK = rolesFK;
    }

    public Departments getDepartmentsFK() {
        return DepartmentsFK;
    }

    public void setDepartmentsFK(Departments departmentsFK) {
        DepartmentsFK = departmentsFK;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
