package com.example.finalpract.Controller;

import com.example.finalpract.Models.Departments;
import com.example.finalpract.Models.Transports;
import com.example.finalpract.Repository.TransportsRepository;
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
public class TransportsController {
    @Autowired
    TransportsRepository transportsRepository;

    @GetMapping("/Transports")
    public String getAllTransports(Model model){
        model.addAttribute("Transports", transportsRepository.findAll());
        model.addAttribute("Transport", new Transports());
        return "Transports";
    }
    @PostMapping("/Transports/add")
    public String addNewTransports(@Valid @ModelAttribute Transports transports, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Transports", transportsRepository.findAll());
            model.addAttribute("Transport", transports);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "Transports";
        }
        transportsRepository.save(transports);
        return "redirect:/Transports";
    }
    @PostMapping("/Transports/update/{id}")
    public String updateTransports(@Valid @ModelAttribute Transports transports, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Transports", transportsRepository.findAll());
            model.addAttribute("Transport", transports);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "Transports";
        }
        transportsRepository.save(transports);
        return "redirect:/Transports";
    }
    @PostMapping("/Transports/delete/{id}")
    public String deleteTransports(@PathVariable Long id){
        transportsRepository.deleteById(id);
        return "redirect:/Transports";
    }
}
