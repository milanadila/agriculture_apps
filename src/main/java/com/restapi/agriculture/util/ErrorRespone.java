package com.restapi.agriculture.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRespone {
    public int status;
    public String message;
    public String[] errors;
}
