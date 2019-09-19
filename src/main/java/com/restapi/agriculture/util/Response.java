package com.restapi.agriculture.util;


import lombok.Data;

@Data
public class Response <T> {
    private String messsage;
    private T data;
}
