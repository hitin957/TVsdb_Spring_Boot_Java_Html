package com.example.finalpract.Controller;

import com.example.finalpract.Models.Roles;
import com.example.finalpract.Repository.RolesRepository;
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
public class RolesController {
    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/Roles")
    public String getAllRoles(Model model){
        model.addAttribute("Roles", rolesRepository.findAll());
        model.addAttribute("role", new Roles());
        return "Roles";
    }
    @PostMapping("/Roles/add")
    public String addNewRole(@Valid @ModelAttribute Roles roles, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Roles", rolesRepository.findAll());
            model.addAttribute("role", roles);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("rolmessage", objectError.getDefaultMessage());
            }
            return "Roles";
        }
        rolesRepository.save(roles);
        return "redirect:/Roles";
    }
    @PostMapping("/Roles/update/{id}")
    public String updateRole(@Valid @ModelAttribute Roles roles, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Roles", rolesRepository.findAll());
            model.addAttribute("role", roles);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "Roles";
        }
        rolesRepository.save(roles);
        return "redirect:/Roles";
    }
    @PostMapping("/Roles/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        rolesRepository.deleteById(id);
        return "redirect:/Roles";
    }
}