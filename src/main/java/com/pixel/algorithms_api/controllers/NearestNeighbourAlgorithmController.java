package com.pixel.algorithms_api.controllers;

import com.pixel.algorithms_api.model.NNACoordinatesList;
import com.pixel.algorithms_api.model.NNACoordinatesListDTO;
import com.pixel.algorithms_api.services.NearestNeighbourAlgorithmService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/nna")
public class NearestNeighbourAlgorithmController {

    private final NearestNeighbourAlgorithmService nearestNeighbourAlgorithmService;

    public NearestNeighbourAlgorithmController(NearestNeighbourAlgorithmService nearestNeighbourAlgorithmService) {
        this.nearestNeighbourAlgorithmService = nearestNeighbourAlgorithmService;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createRoadWithNNA(@Valid @RequestBody() NNACoordinatesList coordinatesList, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Wrong type of request");
        }
        if (coordinatesList.getCoordinates().size() <= 1) {
            return ResponseEntity.badRequest().body("Must by more than one place");
        }
        NNACoordinatesListDTO result = this.nearestNeighbourAlgorithmService.findRoadWithNNA(coordinatesList);
        return ResponseEntity.ok(result);
    }


}
