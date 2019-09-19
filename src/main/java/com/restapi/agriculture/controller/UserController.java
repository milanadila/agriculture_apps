package com.restapi.agriculture.controller;

import com.restapi.agriculture.dto.UserReqDto;
import com.restapi.agriculture.service.UserService;
import com.restapi.agriculture.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<Response> findAll () {

        Response response = new Response();
        response.setMesssage("Berhasil menampilkan semua data");
        response.setData(userService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id")Integer id) {

        Response response = new Response();
        response.setMesssage("Berhasilkan menampilkan data sesuai Id");
        response.setData(userService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Valid UserReqDto userReqDto) {

        Response response = new Response();
        response.setMesssage("Create User Success");
        response.setData(userService.create(userReqDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id")Integer id, @RequestBody @Validated UserReqDto userReqDto) {

        Response response = new Response();
        response.setMesssage("Update User Success");
        response.setData(userService.update(id, userReqDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id")Integer id) {

        Response response = new Response();
        response.setMesssage("Data Deleted");
        response.setData(userService.findById(id));

        userService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
