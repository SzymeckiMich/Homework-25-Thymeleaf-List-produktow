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
        String statement = productsRepository.validInfosAndAddProduct(product);
        if (!statement.equals("success")) {
            product = null;
        }
        return statement;
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
        model.addAttribute("list", productsRepository.getAll());
        model.addAttribute("priceSum", productsRepository.getPriceSum());
    }
}
