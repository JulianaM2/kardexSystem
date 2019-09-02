package com.example.jumunoz.kardex.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.jumunoz.kardex.model.Product;

public class CommonObjects {
    
    public static List<Product> products() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("name1");
        p1.setPrice(1);
        p1.setQuantity(1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("name2");
        p2.setPrice(1);
        p2.setQuantity(1);

        products.add(p1);
        products.add(p2);
        return products;

    }

    public static Product product() {

        Product p1 = new Product();

        p1.setId(1);
        p1.setName("name1");
        p1.setPrice(1);
        p1.setQuantity(1);

        return p1;
    }

    public static Product productUpdated() {

        Product product = new Product();

        product.setId(1);
        product.setName("name1 updated");
        product.setPrice(100);
        product.setQuantity(1);

        return product;
    }
}
