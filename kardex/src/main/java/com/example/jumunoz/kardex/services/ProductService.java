package com.example.jumunoz.kardex.services;

import java.util.List;

import com.example.jumunoz.kardex.model.Product;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(int id);

    public Product addNewProduct(Product product);

    public Product updateProduct(Product product);

}