package com.pixel.algorithms_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

 @Data
@AllArgsConstructor
public class ValidationResponse implements Serializable {
    private boolean isValid;
}
