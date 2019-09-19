package com.restapi.agriculture.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class TransactionRespDto {


    private Integer idTransaction;


    private Integer idUser;


    private Integer idProduct;


    private String userName;


    private String productName;


    private int quantityProduct;


    private int stockProduct;


    private BigDecimal totalTransaction;

    private BigDecimal balanceUser;

    @DateTimeFormat
    private Date createdDate;
}
