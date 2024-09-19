package com.example.finalpract.Controller;

import com.example.finalpract.Models.Orders;
import com.example.finalpract.Models.TVs;
import com.example.finalpract.Repository.OrdersRepository;
import com.example.finalpract.Repository.TVsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@Controller
public class OrdersController {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    TVsRepository tVsRepository;
    @GetMapping("/Orders/Users")
    public String getAllOrdersUser(Model model){
        Iterable<TVs> tVs = tVsRepository.findAll();
        model.addAttribute("tvs", tVs);
        model.addAttribute("Orders", ordersRepository.findAll());
        model.addAttribute("Order", new Orders());
        return "Orders";
    }
    @GetMapping("/Orders/Manager")
    public String getAllOrders(Model model){
        Iterable<TVs> tVs = tVsRepository.findAll();
        model.addAttribute("tvs", tVs);
        model.addAttribute("Orders", ordersRepository.findAll());
        model.addAttribute("Order", new Orders());
        return "OrdersMan";
    }

    @GetMapping("/TVs/Views/Users")
    public String getAllTVs(Model model){
        model.addAttribute("TVs", tVsRepository.findAll());
        model.addAttribute("TV", new TVs());
        return "TVsViews";
    }
    @PostMapping("/Orders/Users/add")
    public String addNewOrdersUsers(@Valid @ModelAttribute Orders orders, BindingResult bindingResult, Model model,
                                    @RequestParam (name = "TV", required = false) String tv){
        for (int i=0; i<tVsRepository.findAll().size(); i++){
            if (tVsRepository.findAll().get(i).getName_TV().equals(tv)){
                tVsRepository.findAll().get(i).setOrdersFK(orders);
                orders.setTVsFK(tVsRepository.findAll().stream().filter(element-> element.getName_TV().equals(tv)).collect(Collectors.toList()));
                orders.setPrise(tVsRepository.findAll().get(i).getPrice_TV());
                orders.setNumber_Orders(tVsRepository.findAll().get(i).getId().intValue());
            }
        }
        ordersRepository.save(orders);
        return "redirect:/Orders/Users";
    }
    @PostMapping("/Orders/Users/update/{id}")
    public String updateOrdersUsers(@Valid @ModelAttribute Orders orders, BindingResult bindingResult, Model model,
                                    @RequestParam (name = "TV", required = false) String tv){
        for (int i=0; i<tVsRepository.findAll().size(); i++){
            if (tVsRepository.findAll().get(i).getName_TV().equals(tv)){
                orders.setTVsFK(tVsRepository.findAll().stream().filter(element-> element.getName_TV().equals(tv)).collect(Collectors.toList()));
                orders.setPrise(tVsRepository.findAll().get(i).getPrice_TV());
                orders.setNumber_Orders(tVsRepository.findAll().get(i).getId().intValue());
            }
        }
        ordersRepository.save(orders);
        return "redirect:/Orders/Users";
    }
    @PostMapping("/Orders/Users/delete/{id}")
    public String deleteOrdersUsers(@PathVariable Long id){
        ordersRepository.deleteById(id);
        return "redirect:/Orders/Users";
    }
    @PostMapping("/Orders/Manager/update/{id}")
    public String updateOrdersManager(@Valid @ModelAttribute Orders orders, BindingResult bindingResult, Model model,
                                    @RequestParam (name = "TV", required = false) String tv){
        for (int i=0; i<tVsRepository.findAll().size(); i++){
            if (tVsRepository.findAll().get(i).getName_TV().equals(tv)){
                orders.setTVsFK(tVsRepository.findAll().stream().filter(element-> element.getName_TV().equals(tv)).collect(Collectors.toList()));
                orders.setPrise(tVsRepository.findAll().get(i).getPrice_TV());
                orders.setNumber_Orders(tVsRepository.findAll().get(i).getId().intValue());
            }
        }
        ordersRepository.save(orders);
        return "redirect:/Orders/Manager";
    }
    @PostMapping("/Orders/Manager/delete/{id}")
    public String deleteOrdersManager(@PathVariable Long id){
        ordersRepository.deleteById(id);
        return "redirect:/Orders/Manager";
    }
}
