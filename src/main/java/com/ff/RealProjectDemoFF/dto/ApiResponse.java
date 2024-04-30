package com.ff.RealProjectDemoFF.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiResponse {
    private HttpStatus httpStatus;
    private int statusCode;
    private Object response;
}
