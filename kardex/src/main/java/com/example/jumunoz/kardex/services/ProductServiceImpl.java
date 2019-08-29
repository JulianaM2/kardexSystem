package com.example.jumunoz.kardex.services;

import java.util.ArrayList;
import java.util.List;

import com.example.jumunoz.kardex.model.Product;
import com.example.jumunoz.kardex.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        repo.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void addNewProduct(Product product) {
        repo.save(product);
    }

	@Override
	public void updateProduct(Product product) {
        repo.save(product);
	}
    
}
