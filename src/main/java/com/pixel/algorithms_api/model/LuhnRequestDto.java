package com.pixel.algorithms_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class LuhnRequestDto implements Serializable {
    @NotBlank
    @NotNull
    private String number;
}
