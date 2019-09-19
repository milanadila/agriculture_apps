package com.restapi.agriculture.controller;

import com.restapi.agriculture.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/produk")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messsage", Matchers.is("Berhasil menampilkan semua data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findById() throws Exception{
        int id = 1;
        mockMvc.perform(get("/produk/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messsage", Matchers.is("Berhasilkan menampilkan data sesuai Id")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void create() throws Exception {
        String json = "{\n" +
                "  \"productName\": \"string\",\n" +
                "  \"productPrice\": 2000,\n" +
                "  \"stockProduct\":3\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.productName", Matchers.is("string"))));

        mockMvc.perform(post("/produk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messsage", Matchers.is("Add Product Success")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void updateById() throws Exception {
        int id = 1;
        String json = "{\n" +
                "  \"productName\": \"string\",\n" +
                "  \"productPrice\": 110,\n" +
                "  \"stockProduct\":10\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.userName", Matchers.is("string"))));
        mockMvc.perform(put("/produk/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messsage", Matchers.is("Update Product Success")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void deleteById() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/produk/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messsage", Matchers.is("Data Deleted")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}
