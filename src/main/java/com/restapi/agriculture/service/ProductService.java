package com.restapi.agriculture.service;

import com.restapi.agriculture.config.AES;
import com.restapi.agriculture.dto.ProductReqDto;
import com.restapi.agriculture.dto.ProductRespDto;
import com.restapi.agriculture.entity.Product;
import com.restapi.agriculture.exception.DataNotFoundException;
import com.restapi.agriculture.repostory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AES aes;

    public List<ProductRespDto> findAll() {

        List<Product> listProduct = productRepository.findAll();
        List<ProductRespDto> listProductDto = new ArrayList<>();
        for (Product product : listProduct) {
            ProductRespDto productRespDto = new ProductRespDto();
            productRespDto.setIdProduct(product.getIdProduct());
            productRespDto.setProductName(product.getProductName());
            productRespDto.setProductPrice(product.getProductPrice());
            productRespDto.setStockProduct(product.getStockProduct());
            productRespDto.setIdProductEncrypt(aes.encrypt(product.getIdProduct().toString()));

            listProductDto.add(productRespDto);
        }

        return listProductDto;
    }

    public ProductRespDto findById(Integer id) {

        Product product = productRepository.findByIdProduct(id);
        ProductRespDto productRespDto = new ProductRespDto();
        productRespDto.setIdProduct(product.getIdProduct());
        productRespDto.setProductName(product.getProductName());
        productRespDto.setProductPrice(product.getProductPrice());
        productRespDto.setStockProduct(product.getStockProduct());
        productRespDto.setIdProductEncrypt(aes.encrypt(product.getIdProduct().toString()));

        return productRespDto;
    }

    public ProductRespDto create (ProductReqDto productReqDto) {
        Product product = new Product();
        product.setProductName(productReqDto.getProductName());
        product.setProductPrice(productReqDto.getProductPrice());
        product.setStockProduct(productReqDto.getStockProduct());

        productRepository.save(product);

        ProductRespDto productRespDto = new ProductRespDto();
        productRespDto.setIdProduct(product.getIdProduct());
        productRespDto.setProductName(product.getProductName());
        productRespDto.setProductPrice(product.getProductPrice());
        productRespDto.setStockProduct(product.getStockProduct());

        return productRespDto;
    }

    public ProductRespDto update (Integer id, ProductReqDto productReqDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) throw new DataNotFoundException("Data Not Found");

        Product product = new Product();
        product.setIdProduct(id);
        product.setProductName(productReqDto.getProductName());
        product.setProductPrice(productReqDto.getProductPrice());
        product.setStockProduct(productReqDto.getStockProduct());

        ProductRespDto productRespDto = new ProductRespDto();
        productRespDto.setIdProduct(id);
        productRespDto.setProductName(product.getProductName());
        productRespDto.setProductPrice(product.getProductPrice());
        productRespDto.setStockProduct(product.getStockProduct());

        productRepository.save(product);
        return productRespDto;
    }

    public void delete (Integer id) {
        productRepository.deleteById(id);
    }

    public void  save (Product product) {
        productRepository.save(product);
    }
}

