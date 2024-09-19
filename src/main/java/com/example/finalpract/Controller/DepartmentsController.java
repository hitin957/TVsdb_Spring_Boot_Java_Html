package com.example.finalpract.Controller;

import com.example.finalpract.Models.Departments;
import com.example.finalpract.Repository.DepartmentsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentsController {
    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping("/Departments")
    public String getAllDepartments(Model model){
        model.addAttribute("Departments", departmentsRepository.findAll());
        model.addAttribute("Department", new Departments());
        return "Departments";
    }

    @PostMapping("/Departments/add")
    public String addNewDepartments(@Valid @ModelAttribute Departments departments, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Departments", departmentsRepository.findAll());
            model.addAttribute("Department", departments);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "Departments";
        }
        departmentsRepository.save(departments);
        return "redirect:/Departments";
    }
    @PostMapping("/Departments/update/{id}")
    public String updateDepartments(@Valid @ModelAttribute Departments departments, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Departments", departmentsRepository.findAll());
            model.addAttribute("Department", departments);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "Departments";
        }
        departmentsRepository.save(departments);
        return "redirect:/Departments";
    }
    @PostMapping("/Departments/delete/{id}")
    public String deleteDepartments(@PathVariable Long id){
        departmentsRepository.deleteById(id);
        return "redirect:/Departments";
    }
}
