package com.example.jumunoz.kardex.services;

import java.util.List;
import java.util.Optional;

import com.example.jumunoz.kardex.model.Product;
import com.example.jumunoz.kardex.repositories.ProductRepository;
import com.example.jumunoz.kardex.utils.CommonObjects;

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

        Mockito.when(productRepository.findAll()).thenReturn(CommonObjects.products());
        List<Product> result = service.getAllProducts();

        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getId()).isEqualTo(2);
    }

    @Test
    public void getAllProductsShouldReturnEmptyListWhenThereAreNoProducts() {

        List<Product> result = service.getAllProducts();

        Assertions.assertThat(result).isEmpty();

    }

    @Test
    public void getProductByIdShouldReturnTheProductWhenTheProductExists() {
        int productId = 1;
        Product product = CommonObjects.products().get(0);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = service.getProductById(productId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(productId);
        Assertions.assertThat(result.getName()).isEqualTo(product.getName());
        Assertions.assertThat(result.getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(result.getQuantity()).isEqualTo(product.getQuantity());

    }

    @Test
    public void getProductByIdShouldReturnNullWhenTheProductDoesNotExist() {
        int productId = 1;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product result = service.getProductById(productId);

        Assertions.assertThat(result).isNull();

    }

    @Test
    public void addNewProductShouldReturnTheProductWhenTheSaveIsSuccess() {

        Product product = CommonObjects.product();

        Mockito.when(productRepository.save(product)).thenReturn(CommonObjects.products().get(0));

        Product result = service.addNewProduct(product);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(product.getId());
        Assertions.assertThat(result.getName()).isEqualTo(product.getName());
        Assertions.assertThat(result.getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(result.getQuantity()).isEqualTo(product.getQuantity());

    }

    @Test
    public void updateProductShouldReturnTheProductWhenTheSaveIsSuccess() {

        Product product = CommonObjects.product();
        product.setName("name1 updated");
        product.setPrice(100);
        Product productUpdated = CommonObjects.productUpdated();

        Mockito.when(productRepository.save(product)).thenReturn(CommonObjects.productUpdated());

        Product result = service.updateProduct(product);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(productUpdated.getId());
        Assertions.assertThat(result.getName()).isEqualTo(productUpdated.getName());
        Assertions.assertThat(result.getPrice()).isEqualTo(productUpdated.getPrice());
        Assertions.assertThat(result.getQuantity()).isEqualTo(productUpdated.getQuantity());

    }
}