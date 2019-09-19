package com.restapi.agriculture.dto;



import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class UserReqDto {



    @Length(max = 25, message = "Name max 20 character")
    @NotEmpty(message = "Name Must be Filled")
    private String userName;


    @PositiveOrZero
    @NotNull(message = "Balance Must be Filled")
    private BigDecimal balanceUser;
}
