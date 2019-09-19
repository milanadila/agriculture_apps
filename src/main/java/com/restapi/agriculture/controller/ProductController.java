package com.restapi.agriculture.controller;


import com.restapi.agriculture.dto.ProductReqDto;
import com.restapi.agriculture.service.ProductService;
import com.restapi.agriculture.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "produk")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    ResponseEntity<Response> findAll() {

        Response response = new Response();
        response.setMesssage("Berhasil menampilkan semua data");
        response.setData(productService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id")Integer id) {

        Response response = new Response();
        response.setMesssage("Berhasilkan menampilkan data sesuai Id");
        response.setData(productService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Valid ProductReqDto productReqDto) {

        Response response = new Response();
        response.setMesssage("Add Product Success");
        response.setData(productService.create(productReqDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id")Integer id, @RequestBody @Validated ProductReqDto productReqDto) {

        Response response = new Response();
        response.setMesssage("Update Product Success");
        response.setData(productService.update(id, productReqDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id")Integer id) {

        Response response = new Response();
        response.setMesssage("Data Deleted");
        response.setData(productService.findById(id));

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);


    }
}
