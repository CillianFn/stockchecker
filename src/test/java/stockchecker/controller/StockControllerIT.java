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
import stockchecker.model.Stock;
import stockchecker.service.ProductService;
import stockchecker.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockController.class)
@ActiveProfiles("test")
public class StockControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetStock() throws Exception {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock expectedStock = new Stock(product);

        given(stockService.getStock(expectedStock.getId())).willReturn(Optional.of(expectedStock));

        this.mockMvc.perform(get("/stock/{id}", expectedStock.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expectedStock.getId())))
                .andExpect(jsonPath("$.amount", is(expectedStock.getAmount())))
                .andExpect(jsonPath("$.inStock", is(expectedStock.isInStock())))
                .andExpect(jsonPath("$.product.id", is(expectedStock.getProduct().getId())))
                .andExpect(jsonPath("$.product.name", is(expectedStock.getProduct().getName())))
                .andExpect(jsonPath("$.product.description", is(expectedStock.getProduct().getDescription())));
    }

    @Test
    public void testGetNonExistentStock() throws Exception {
        this.mockMvc.perform(get("/stock/{id}", "test-1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetStockMultiple() throws Exception {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Product product1 = new Product("test-1235", "Filter Coffee - 250g", "Blend");
        Product product2 = new Product("test-1236", "Filter Coffee - 1000g", "Blend");

        Stock expectedStock = new Stock(product);
        Stock expectedStock1 = new Stock(product1);
        Stock expectedStock2 = new Stock(product2);

        List<Stock> expectedStockList = new ArrayList<>(Arrays.asList(expectedStock, expectedStock1, expectedStock2));

        given(stockService.getStock()).willReturn(expectedStockList);

        this.mockMvc.perform(get("/stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock.size()", is(expectedStockList.size())));
    }

    @Test
    public void testPatchStock() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock expectedStock = new Stock(product);

        given(stockService.patchStock(any(Stock.class))).willReturn(expectedStock);
        given(productService.getProduct(expectedStock.getId())).willReturn(Optional.of(product));

        this.mockMvc.perform(patch("/stock/{id}", expectedStock.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedStock)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", is(expectedStock.getId())))
                .andExpect(jsonPath("$.amount", is(expectedStock.getAmount())))
                .andExpect(jsonPath("$.inStock", is(expectedStock.isInStock())))
                .andExpect(jsonPath("$.product.id", is(expectedStock.getProduct().getId())))
                .andExpect(jsonPath("$.product.name", is(expectedStock.getProduct().getName())))
                .andExpect(jsonPath("$.product.description", is(expectedStock.getProduct().getDescription())));
    }

    @Test
    public void testPatchStockOfNonExistentProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock expectedStock = new Stock(product);

        given(stockService.patchStock(any(Stock.class))).willReturn(expectedStock);

        this.mockMvc.perform(patch("/stock/{id}", expectedStock.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedStock)))
                .andExpect(status().isNotFound());
    }
}
