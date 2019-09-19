package com.restapi.agriculture.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProductReqDto {

    @NotEmpty(message = "Product Name Must be Filled")
    @Length(max = 15, message = "Name max 20 character")
    private String productName;

    @PositiveOrZero
    @NotNull(message = "Product Price Must be Filled")
    private BigDecimal productPrice;

    @PositiveOrZero
    @NotNull(message = "Stock Product Must be Filled")
    private int stockProduct;


}
