package com.example.jumunoz.kardex.repositories;

import com.example.jumunoz.kardex.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}