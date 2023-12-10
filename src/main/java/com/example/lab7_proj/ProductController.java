package com.example.lab7_proj;


import com.example.lab7_proj.Model.Product;
import com.example.lab7_proj.Model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewCart(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("newProduct", new Product()); // Для передачи нового продукта в форму
        return "cart";
    }
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Long id) {
        productService.removeProduct(id);
        return "redirect:/"; // Перенаправление на главную страницу корзины
    }
    @PostMapping("/addToCart")
    public String addToCart(Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }
}
