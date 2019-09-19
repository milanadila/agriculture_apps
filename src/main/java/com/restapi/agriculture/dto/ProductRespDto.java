package com.restapi.agriculture.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRespDto {


    private Integer idProduct;


    private String productName;


    private BigDecimal productPrice;


    private int stockProduct;

    private String idProductEncrypt;

}
