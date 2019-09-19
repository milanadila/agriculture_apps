package com.restapi.agriculture.controller;


import com.restapi.agriculture.dto.TransactionReqDto;
import com.restapi.agriculture.service.TransactionService;
import com.restapi.agriculture.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    ResponseEntity<Response> addTransaction(@RequestBody @Valid TransactionReqDto transactionReqDto) {

        Response response = new Response();
        response.setMesssage("Transaction Success");
        response.setData(transactionService.create(transactionReqDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @GetMapping
    ResponseEntity<Response> findAll() {

        Response response = new Response();
        response.setMesssage("Berhasil menampilkan semua data");
        response.setData(transactionService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id")Integer id) {

        Response response = new Response();
        response.setMesssage("Berhasilkan menampilkan data sesuai Id");
        response.setData(transactionService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
