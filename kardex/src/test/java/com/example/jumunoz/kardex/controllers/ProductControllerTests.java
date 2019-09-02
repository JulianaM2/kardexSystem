package com.example.jumunoz.kardex.controllers;

import java.util.Collections;
import java.util.List;

import com.example.jumunoz.kardex.model.Product;
import com.example.jumunoz.kardex.services.ProductServiceImpl;
import com.example.jumunoz.kardex.utils.CommonObjects;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController controller; // sut

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllProductsShouldWorkWhenIsEmpty() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/kardex/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(Matchers.empty())));
    }

    @Test
    public void getAllProductsShouldWork() throws Exception {

        List<Product> products = CommonObjects.products();

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/kardex/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(products.get(0).getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Matchers.is(products.get(0).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", Matchers.is(products.get(0).getPrice())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quantity", Matchers.is(products.get(0).getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Matchers.is(products.get(1).getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name", Matchers.is(products.get(1).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price", Matchers.is(products.get(1).getPrice())))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.[1].quantity", Matchers.is(products.get(1).getQuantity())));
    }

    @Test
    public void getProductByIdShouldWork() throws Exception {

        Product products = CommonObjects.products().get(0);
        int productId = 1;

        Mockito.when(productService.getProductById(productId)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/kardex/product/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(products.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(products.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(products.getPrice())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(products.getQuantity())));              
    }

    @Test
    public void addNewProductShouldWork() throws Exception {

        Product products = CommonObjects.products().get(0);
        Product product = CommonObjects.product();
        String content = new JSONObject()
        .put("name", product.getName())
        .put("price", product.getPrice())
        .put("quantity", product.getQuantity())
        .toString();

        Mockito.when(productService.addNewProduct(product)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.post("/kardex/product/add").contentType(MediaType.APPLICATION_JSON)
                .content(content)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}