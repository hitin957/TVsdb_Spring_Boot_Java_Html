package com.example.finalpract.Controller;

import com.example.finalpract.Models.TVs;
import com.example.finalpract.Repository.TVsRepository;
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
public class TVsController {
    @Autowired
    TVsRepository tVsRepository;

    @GetMapping("/TVs")
    public String getAllTVs(Model model){
        model.addAttribute("TVs", tVsRepository.findAll());
        model.addAttribute("TV", new TVs());
        return "TVs";
    }
    @PostMapping("/TVs/add")
    public String addNewTV(@Valid @ModelAttribute TVs tVs, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("TVs", tVsRepository.findAll());
            model.addAttribute("TV", tVs);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "TVs";
        }
        tVsRepository.save(tVs);
        return "redirect:/TVs";
    }
    @PostMapping("/TVs/update/{id}")
    public String updateTVs(@Valid @ModelAttribute TVs tVs, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("TVs", tVsRepository.findAll());
            model.addAttribute("TV", tVs);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "TVs";
        }
        tVsRepository.save(tVs);
        return "redirect:/TVs";
    }
    @PostMapping("/TVs/delete/{id}")
    public String deleteTVs(@PathVariable Long id){
        tVsRepository.deleteById(id);
        return "redirect:/TVs";
    }
}
