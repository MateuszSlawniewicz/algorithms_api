package com.pixel.algorithms_api.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class NNACoordinatesList implements Serializable {
    @NotNull
    private List<Coordinate> coordinates = new ArrayList<>();
}
