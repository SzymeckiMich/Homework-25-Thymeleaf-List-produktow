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
    String addProduct(){
        return "addProduct";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }


    @PostMapping("/add")
    String add(@RequestParam String name, @RequestParam(required = true) String price) {
        double priceValue;
        String prodName = name;

        if (null==prodName || "".equals(prodName)) return "emptyNameError";
        try {
            priceValue = Double.parseDouble(price);
        } catch (NumberFormatException ex) {
            return "numberFormatError";
        }
        if (priceValue == 0) {
            return "zeroPriceError";
        } else {
            productsRepository.addProduct(new Product(prodName, priceValue));
            return "success";
        }
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
    private void addToModel(Model model){
        DecimalFormat formatter = new DecimalFormat("0.00zł");
        String sum = formatter.format(productsRepository.getPriceSum());
        model.addAttribute("list", productsRepository.getAll());
        model.addAttribute("priceSum", sum);
    }
}
