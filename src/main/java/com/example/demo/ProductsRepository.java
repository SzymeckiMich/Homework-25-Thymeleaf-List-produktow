package com.example.demo;

import org.springframework.stereotype.Repository;

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


}
