package com.pixel.algorithms_api.services;

import com.pixel.algorithms_api.model.NNACoordinatesList;
import com.pixel.algorithms_api.model.NNACoordinatesListDTO;

public interface NearestNeighbourAlgorithmService {
    NNACoordinatesListDTO findRoadWithNNA(NNACoordinatesList coordinatesList);
}

