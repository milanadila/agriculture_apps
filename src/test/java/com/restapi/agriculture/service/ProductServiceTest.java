//package com.restapi.agriculture.service;
//
//import com.restapi.agriculture.dto.ProductReqDto;
//import com.restapi.agriculture.dto.ProductRespDto;
//import com.restapi.agriculture.entity.Product;
//import com.restapi.agriculture.exception.DataNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@Slf4j
//public class ProductServiceTest {
//
//    @Autowired
//    private ProductService productService;
//
//    @Test
//    public void findAll() {
//        List<Product> productRespDtoTest = productService.findAll();
//        assertThat(productRespDtoTest.size()).isNotNull();
//        for (int i = 0; i < productRespDtoTest.size(); i++) {
//        }
//    }
//
//    @Test
//    @Transactional
//    public void findbyId() {
//        Integer id = 1;
//        Product product = productService.findById(id);
//
//        assertThat(product)
//                .hasFieldOrProperty("idProduct")
//                .hasFieldOrProperty("productName")
//                .hasFieldOrProperty("productPrice")
//                .hasFieldOrProperty("stockProduct");
//    }
//
//    @Test
//    public void create() {
//        ProductReqDto productReqDto = new ProductReqDto();
//
//        String productName = "Lele";
//        BigDecimal productPrice = BigDecimal.valueOf(25000);
//        int stockProduct = 6;
//
//        productReqDto.setProductName(productName);
//        productReqDto.setProductPrice(productPrice);
//        productReqDto.setStockProduct(stockProduct);
//
//        ProductRespDto productRespDto = productService.create(productReqDto);
//        assertThat(productRespDto.getProductName()).isEqualTo(productName);
//        assertThat(productRespDto.getProductPrice()).isEqualTo(productPrice);
//        assertThat(productRespDto.getStockProduct()).isEqualTo(stockProduct);
//    }
//
//    @Test
//    @Transactional
//    public void update() {
//        ProductReqDto productReqDto = new ProductReqDto();
//
//        Integer id = 1;
//        String productName = "Ayam";
//        BigDecimal productPrice = BigDecimal.valueOf(20000);
//        int stockProduct = 10;
//
//        productReqDto.setProductName(productName);
//        productReqDto.setProductPrice(productPrice);
//        productReqDto.setStockProduct(stockProduct);
//        ProductRespDto productRespDto = productService.update(id, productReqDto);
//        assertThat(productRespDto.getProductName()).isEqualTo(productName);
//        assertThat(productRespDto.getProductPrice()).isEqualTo(productPrice);
//        assertThat(productRespDto.getStockProduct()).isEqualTo(stockProduct);
//    }
//
//    @Test
//    public void delete() {
//        Integer id = 3;
//        List<Product> products = productService.findAll();
//        productService.delete(id);
//
//        List<Product> productServiceAll = productService.findAll();
//        assertThat(products.size()).isGreaterThan(productServiceAll.size());
//    }
//
//    @Test(expected = DataNotFoundException.class)
//    @Transactional
//    public void failedById() {
//        Integer id = 40;
//        Product product = productService.findById(id);
//        assertThat(product.getIdProduct()).isNotEqualTo(id);
//    }
//}
