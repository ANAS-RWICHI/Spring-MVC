package com.example.spring_mvc.web;

import com.example.spring_mvc.entities.Product;
import com.example.spring_mvc.repository.ProductRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping({"/","index"})
    public String index(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("productsList", products);
        return "products";
    };
    @GetMapping("/delete")
    public String delete( @RequestParam(name="id") Long id) {
        productRepo.deleteById(id);
        return "redirect:/index";
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product , BindingResult bindingResult , Model model) {
        if (bindingResult.hasErrors()) {
            return "newProduct";
        }
        productRepo.save(product);
        return "redirect:/index";
    }
    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "notAuthorized";}
}
