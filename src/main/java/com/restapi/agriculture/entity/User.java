package com.restapi.agriculture.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id" )
    private Integer idUser;

    @Column(name = "username")
    private String userName;

    @Column(name = "Balance")
    private BigDecimal balanceUser;

}
