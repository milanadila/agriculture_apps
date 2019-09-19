package com.restapi.agriculture.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_Id")
    private Integer idTransaction;

    @Column(name = "User_Id")
    private Integer idUser;

    @Column(name = "Product_Id")
    private Integer idProduct;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Product_Name")
    private String productName;

    @Column(name = "Total_Transaction")
    private BigDecimal totalTransaction;

    @Column(name = "Created_Date")
    private Date createdDate;

    @Column(name = "Qty_Product")
    private int quantityProduct;

    @Column(name = "Balance_User")
    private BigDecimal balanceUser;

}
