package com.example.smallbankApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse {
    private int statusCode;
    private String description;
    private Object data;
    private Object error;

}
