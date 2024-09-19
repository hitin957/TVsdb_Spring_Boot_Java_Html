package com.example.finalpract.Controller;

import com.example.finalpract.Models.Staffs;
import com.example.finalpract.Repository.RolesRepository;
import com.example.finalpract.Repository.StaffsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MainController {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private StaffsRepository staffsRepository;
    @GetMapping
    public String Auth(){
        return "Auth";
    }
    @PostMapping
    public String AuthOK(Model model, @RequestParam(name = "Login", required = false) String login,
                         @RequestParam(name = "Password", required = false) String password){
        for (int i = 0; i<staffsRepository.findAll().size(); i++){
            if (staffsRepository.findAll().get(i).getLogin().equals(login) && staffsRepository.findAll().get(i).getPassword().equals(password)){
                if (staffsRepository.findAll().get(i).getRolesFK().getName_role().equals("Admin"))
                    return "Admin";
                if (staffsRepository.findAll().get(i).getRolesFK().getName_role().equals("Users"))
                    return "User";
                if (staffsRepository.findAll().get(i).getRolesFK().getName_role().equals("Manager"))
                    return "Manager";
            }
        }
        model.addAttribute("Authmessage", "Неверный логин или пароль");
        return "Auth";
    }
    @GetMapping("/Registration/")
    public String Registration(){
        return "Registration";
    }
    @PostMapping("/RegistrationOK/")
    public String RegistrationOK(@Valid @ModelAttribute Staffs staffs, BindingResult bindingResult, Model model,
                                 @RequestParam(name = "date", required = false) Date date){
        if (bindingResult.hasErrors()){
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "Registration";
        }
        staffs.setDate_of_birth(date.toLocalDate());
        staffs.setRolesFK(rolesRepository.findAll().get(1));
        staffsRepository.save(staffs);
        return "Auth";
    }
    @GetMapping("/User/")
    public String User(){
        return "User";
    }
    @GetMapping("/Admin/")
    public String Admin(){
        return "Admin";
    }
    @GetMapping("/Manager/")
    public String Manager(){
        return "Manager";
    }
}
