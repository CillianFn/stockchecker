package stockchecker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import stockchecker.model.Product;
import stockchecker.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stockchecker.TestUtils.getProduct;


@WebMvcTest(controllers = ProductController.class)
@ActiveProfiles("test")
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProduct() throws Exception {
        Product product = getProduct();

        given(productService.getProduct(product.getId())).willReturn(Optional.of(product));

        this.mockMvc.perform(get("/product/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())));
    }

    @Test
    public void testGetNonExistentProduct() throws Exception {
        this.mockMvc.perform(get("/product/{id}", "test-1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProducts() throws Exception {
        Product product = getProduct();
        Product product1 = new Product("test-1235", "Filter Coffee - 250g", "Blend");
        Product product2 = new Product("test-1236", "Filter Coffee - 1000g", "Blend");

        List<Product> expectedProducts = new ArrayList<>(Arrays.asList(product, product1, product2));

        given(productService.getProducts()).willReturn(expectedProducts);

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products.size()", is(expectedProducts.size())));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = getProduct();

        this.mockMvc.perform(delete("/product/{id}", product.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = getProduct();

        given(productService.addProduct(product)).willReturn(product);

        this.mockMvc.perform(put("/product/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())));
    }
}
