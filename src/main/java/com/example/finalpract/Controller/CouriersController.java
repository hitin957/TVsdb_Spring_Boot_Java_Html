package com.example.finalpract.Controller;

import com.example.finalpract.Models.Couriers;
import com.example.finalpract.Models.Staffs;
import com.example.finalpract.Models.Suppliers;
import com.example.finalpract.Models.Transports;
import com.example.finalpract.Repository.CouriersRepository;
import com.example.finalpract.Repository.StaffsRepository;
import com.example.finalpract.Repository.TransportsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
public class CouriersController {
    @Autowired
    CouriersRepository couriersRepository;
    @Autowired
    public StaffsRepository staffsRepository;
    @Autowired
    public TransportsRepository transportsRepository;

    @GetMapping("/Couriers")
    public String getAllCouriers(Model model){
        Iterable<Staffs> staffs = staffsRepository.findAll();
        Iterable<Transports> transports = transportsRepository.findAll();
        model.addAttribute("Staffs", staffs);
        model.addAttribute("Transports", transports);
        model.addAttribute("Suppliers", couriersRepository.findAll());
        model.addAttribute("Supplier", new Couriers());
        return "Couriers";
    }
    @PostMapping("/Couriers/add")
    public String addNewCouriers(@Valid @ModelAttribute Couriers couriers, BindingResult bindingResult, Model model,
                                  @RequestParam(name = "staff", required = false) String staff,
                                  @RequestParam(name = "trans", required = false) String trans){
        if (bindingResult.hasErrors()){
            Iterable<Staffs> staffs = staffsRepository.findAll();
            Iterable<Transports> transports = transportsRepository.findAll();
            model.addAttribute("Staffs", staffs);
            model.addAttribute("Transports", transports);
            model.addAttribute("Suppliers", couriersRepository.findAll());
            model.addAttribute("Supplier", new Couriers());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "Couriers";
        }
        for (int i = 0; i<staffsRepository.findAll().size(); i++){
            if (staffsRepository.findAll().get(i).getFIO().equals(staff)){
                couriers.setStaffsFK(staffsRepository.findAll().get(i));
            }
        }
        for (int i = 0; i<transportsRepository.findAll().size(); i++){
            if (transportsRepository.findAll().get(i).getName_transport().equals(trans)){
                couriers.setTransportsFK(transportsRepository.findAll().get(i));
            }
        }
        couriersRepository.save(couriers);
        return "redirect:/Couriers";
    }

    @PostMapping("/Couriers/update/{id}")
    public String updateCouriers(@Valid @ModelAttribute Couriers couriers, BindingResult bindingResult, Model model,
                                  @RequestParam(name = "staff", required = false) String staff,
                                  @RequestParam(name = "trans", required = false) String trans){
        if (bindingResult.hasErrors()){
            Iterable<Staffs> staffs = staffsRepository.findAll();
            Iterable<Transports> transports = transportsRepository.findAll();
            model.addAttribute("Staffs", staffs);
            model.addAttribute("Transports", transports);
            model.addAttribute("Suppliers", couriersRepository.findAll());
            model.addAttribute("Supplier", new Couriers());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "Couriers";
        }
        for (int i = 0; i<staffsRepository.findAll().size(); i++){
            if (staffsRepository.findAll().get(i).getFIO().equals(staff)){
                couriers.setStaffsFK(staffsRepository.findAll().get(i));
            }
        }
        for (int i = 0; i<transportsRepository.findAll().size(); i++){
            if (transportsRepository.findAll().get(i).getName_transport().equals(trans)){
                couriers.setTransportsFK(transportsRepository.findAll().get(i));
            }
        }
        couriersRepository.save(couriers);
        return "redirect:/Couriers";
    }
    @PostMapping("/Couriers/delete/{id}")
    public String deleteCouriers(@PathVariable Long id){
        couriersRepository.deleteById(id);
        return "redirect:/Couriers";
    }
}
