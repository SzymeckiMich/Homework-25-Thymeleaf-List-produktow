package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsRepository {
    private double priceSum = 0;

    private List<Product> products;

    public ProductsRepository() {
        products = new ArrayList<>();
        addProduct(new Product("mleko", 100.55));
        addProduct(new Product("chleb", 2.25));
    }


    public void addProduct(Product product) {
        priceSum += product.getPrice();
        products.add(product);
    }

    public double getPriceSum() {
        return priceSum;
    }

    public List<Product> getAll() {
        return new ArrayList<>(products);
    }


    public String validInfosAndAddProduct(Product product, Model model) {

        if ("".equals(product.getName().trim()) || null == product.getName()) {
            model.addAttribute("statement", "Nie dodano nazwy");
            return "error";
        }
        try {
            product.setPrice(Double.parseDouble(product.getPriceOnString()));
            if (product.getPrice() == 0) {
                model.addAttribute("statement", "Cena nie może wynosić 0,00zł");
                return "error";
            }
        } catch (NumberFormatException ex) {
            model.addAttribute("statement", "Podana cena nie jest liczbą");
            return "error";
        }

        addProduct(product);
        return "success";
    }

}
