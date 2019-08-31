package com.example.jumunoz.kardex.services;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jumunoz.kardex.model.Product;
import com.example.jumunoz.kardex.repositories.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl service; // System Under Test (sut)

    @Test
    public void getAllProductsShouldReturnListWhenThereAreProducts() {

        Mockito.when(productRepository.findAll()).thenReturn(products());
        List<Product> result = service.getAllProducts();

        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
    }

    @Test
    public void getAllProductsShouldReturnEmptyListWhenThereAreNoProducts() {

        List<Product> result = service.getAllProducts();

        Assertions.assertThat(result).isEmpty();
       
    }

    @Test
    public void getProductByIdShouldReturnTheProductWhenTheProductExists() {
        int productId = 1;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(products().get(0)));

        Product result = service.getProductById(productId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(productId);
               
    }

    @Test
    public void getProductByIdShouldReturnNullWhenTheProductDoesNotExist() {
        int productId = 1;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product result = service.getProductById(productId);

        Assertions.assertThat(result).isNull();
               
    }

    

    private List<Product> products() {
        List<Product> products =  new ArrayList<>();
        Product p1 = new Product();
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

    private Product product() {
        
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("name1");
        p1.setPrice(1);
        p1.setQuantity(1);

        return p1;

    }
}