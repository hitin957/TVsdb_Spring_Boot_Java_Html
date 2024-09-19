package com.example.finalpract.Controller;

import com.example.finalpract.Models.Roles;
import com.example.finalpract.Models.Staffs;
import com.example.finalpract.Repository.RolesRepository;
import com.example.finalpract.Repository.StaffsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class StaffsController {
    @Autowired
    public StaffsRepository staffsRepository;
    @Autowired
    public RolesRepository rolesRepository;
    @GetMapping("/Staffs")
    public String getAllStaffs(Model model){
        Iterable<Roles> roles = rolesRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("Staffs", staffsRepository.findAll());
        model.addAttribute("staff", new Staffs());
        return "Staffs";
    }
    @PostMapping("/Staffs/add")
    public String addNewStaffs(@Valid @ModelAttribute Staffs staffs, BindingResult bindingResult, Model model,
                               @RequestParam(name = "roles", required = false) String role,
                               @RequestParam(name = "date", required = false) Date date){
        if (bindingResult.hasErrors()){
            Iterable<Roles> roles = rolesRepository.findAll();
            model.addAttribute("roles", roles);
            model.addAttribute("Staffs", staffsRepository.findAll());
            model.addAttribute("staff", new Staffs());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("stafmessage", objectError.getDefaultMessage());
            }
            return "Staffs";
        }
        for (int i = 0; i<rolesRepository.findAll().size(); i++)
            if (rolesRepository.findAll().get(i).getName_role().equals(role)){
                staffs.setRolesFK(rolesRepository.findAll().get(i));
            }
        staffs.setDate_of_birth(date.toLocalDate());
        staffsRepository.save(staffs);
        return "redirect:/Staffs";
    }
    @PostMapping("/Staffs/update/{id}")
    public String updateStaffs(@Valid @ModelAttribute Staffs staffs, BindingResult bindingResult, Model model,
                               @RequestParam(name = "roles", required = false) String role,
                               @RequestParam(name = "date", required = false) Date date){
        if (bindingResult.hasErrors()){
            Iterable<Roles> roles = rolesRepository.findAll();
            model.addAttribute("roles", roles);
            model.addAttribute("Staffs", staffsRepository.findAll());
            model.addAttribute("staff", new Staffs());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_stafmessage", objectError.getDefaultMessage());
            }
            return "Staffs";
        }
        for (int i = 0; i<rolesRepository.findAll().size(); i++)
            if (rolesRepository.findAll().get(i).getName_role().equals(role)){
                staffs.setRolesFK(rolesRepository.findAll().get(i));
            }
        staffs.setDate_of_birth(date.toLocalDate());
        staffsRepository.save(staffs);
        return "redirect:/Staffs";
    }
    @PostMapping("/Staffs/delete/{id}")
    public String deleteStaffs(@PathVariable Long id){
        staffsRepository.deleteById(id);
        return "redirect:/Staffs";
    }
}
