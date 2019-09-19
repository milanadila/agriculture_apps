package com.restapi.agriculture.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class TransactionReqDto {

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idProduct;

    @NotNull
    private int quantityProduct;


    @DateTimeFormat
    private Date createdDate;

}
