package com.example.jumunoz.kardex.controllers;

import java.util.List;

import com.example.jumunoz.kardex.model.Product;
import com.example.jumunoz.kardex.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/kardex/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/add")
    public void addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
        product.setId(id);
        productService.updateProduct(product);
    }

}