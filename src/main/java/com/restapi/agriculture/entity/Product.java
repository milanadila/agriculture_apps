package com.restapi.agriculture.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id" )
    private Integer idProduct;

    @Column(name = "Name")
    private String productName;

    @Column(name = "Price")
    private BigDecimal productPrice;

    @Column(name = "Stock_Product")
    private int stockProduct;

}
