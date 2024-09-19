package com.example.finalpract.Controller;

import com.example.finalpract.Models.Staffs;
import com.example.finalpract.Models.Suppliers;
import com.example.finalpract.Models.Transports;
import com.example.finalpract.Repository.StaffsRepository;
import com.example.finalpract.Repository.SuppliersRepository;
import com.example.finalpract.Repository.TransportsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuppliersController {
    @Autowired
    SuppliersRepository suppliersRepository;
    @Autowired
    public StaffsRepository staffsRepository;
    @Autowired
    public TransportsRepository transportsRepository;

    @GetMapping("/Suppliers")
    public String getAllSuppliers(Model model){
        Iterable<Staffs> staffs = staffsRepository.findAll();
        Iterable<Transports> transports = transportsRepository.findAll();
        model.addAttribute("Staffs", staffs);
        model.addAttribute("Transports", transports);
        model.addAttribute("Suppliers", suppliersRepository.findAll());
        model.addAttribute("Supplier", new Suppliers());
        return "Suppliers";
    }
    @PostMapping("/Suppliers/add")
    public String addNewSuppliers(@Valid @ModelAttribute Suppliers suppliers, BindingResult bindingResult, Model model,
                                  @RequestParam(name = "staff", required = false) String staff,
                                  @RequestParam(name = "trans", required = false) String trans){
        if (bindingResult.hasErrors()){
            Iterable<Staffs> staffs = staffsRepository.findAll();
            Iterable<Transports> transports = transportsRepository.findAll();
            model.addAttribute("Staffs", staffs);
            model.addAttribute("Transports", transports);
            model.addAttribute("Suppliers", suppliersRepository.findAll());
            model.addAttribute("Supplier", new Suppliers());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "Suppliers";
        }
        for (int i = 0; i<staffsRepository.findAll().size(); i++){
            if (staffsRepository.findAll().get(i).getFIO().equals(staff)){
                suppliers.setStaffsFK(staffsRepository.findAll().get(i));
            }
        }
        for (int i = 0; i<transportsRepository.findAll().size(); i++){
            if (transportsRepository.findAll().get(i).getName_transport().equals(trans)){
                suppliers.setTransportsFK(transportsRepository.findAll().get(i));
            }
        }
        suppliersRepository.save(suppliers);
        return "redirect:/Suppliers";
    }

    @PostMapping("/Suppliers/update/{id}")
    public String updateSuppliers(@Valid @ModelAttribute Suppliers suppliers, BindingResult bindingResult, Model model,
                                  @RequestParam(name = "staff", required = false) String staff,
                                  @RequestParam(name = "trans", required = false) String trans){
        if (bindingResult.hasErrors()){
            Iterable<Staffs> staffs = staffsRepository.findAll();
            Iterable<Transports> transports = transportsRepository.findAll();
            model.addAttribute("Staffs", staffs);
            model.addAttribute("Transports", transports);
            model.addAttribute("Suppliers", suppliersRepository.findAll());
            model.addAttribute("Supplier", new Suppliers());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "Suppliers";
        }
        for (int i = 0; i<staffsRepository.findAll().size(); i++){
            if (staffsRepository.findAll().get(i).getFIO().equals(staff)){
                suppliers.setStaffsFK(staffsRepository.findAll().get(i));
            }
        }
        for (int i = 0; i<transportsRepository.findAll().size(); i++){
            if (transportsRepository.findAll().get(i).getName_transport().equals(trans)){
                suppliers.setTransportsFK(transportsRepository.findAll().get(i));
            }
        }
        suppliersRepository.save(suppliers);
        return "redirect:/Suppliers";
    }
    @PostMapping("/Suppliers/delete/{id}")
    public String deleteSuppliers(@PathVariable Long id){
        suppliersRepository.deleteById(id);
        return "redirect:/Suppliers";
    }
}
