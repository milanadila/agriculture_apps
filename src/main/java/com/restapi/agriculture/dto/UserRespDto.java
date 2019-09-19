package com.restapi.agriculture.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserRespDto {


    private Integer idUser;


    private String userName;


    private BigDecimal balanceUser;

    private String idUserEncrypt;


}
