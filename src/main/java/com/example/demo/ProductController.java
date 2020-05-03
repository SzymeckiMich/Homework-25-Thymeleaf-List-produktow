package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

import static java.util.Objects.nonNull;


@Controller
@RequestMapping("/")
public class ProductController {

    private ProductsRepository productsRepository;

    public ProductController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }


    @PostMapping("/add")
    String add(Product product) {
        return productsRepository.validInfosAndAddProduct(product);
    }


    @GetMapping("/list")
    String list(Model model) {
        addToModel(model);
        return "list";
    }

    @GetMapping("/table")
    String table(Model model) {
        addToModel(model);
        return "table";
    }

    private void addToModel(Model model) {
        DecimalFormat formatter = new DecimalFormat("0.00zł");
        String sum = formatter.format(productsRepository.getPriceSum());
        model.addAttribute("list", productsRepository.getAll());
        model.addAttribute("priceSum", sum);
    }
}
