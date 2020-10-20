package com.pixel.algorithms_api.controllers;


import com.pixel.algorithms_api.model.CheckSumResponse;
import com.pixel.algorithms_api.model.LuhnRequestDto;
import com.pixel.algorithms_api.model.ValidationResponse;
import com.pixel.algorithms_api.services.LuhnAlgorithmService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/luhnAlgorithm")
public class LuhnAlgorithmController {

    private final LuhnAlgorithmService luhnAlgorithmService;

    public LuhnAlgorithmController(LuhnAlgorithmService luhnAlgorithmService) {
        this.luhnAlgorithmService = luhnAlgorithmService;
    }

    @CrossOrigin
    @RequestMapping(value = "/valid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkIsValidNumber(@Valid @RequestBody LuhnRequestDto req, Errors errors) {
        if (errors.hasErrors() || !req.getNumber().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body("Wrong type of request");
        }
        boolean result = this.luhnAlgorithmService.validateNumber(req.getNumber());
        ValidationResponse validationResponse = new ValidationResponse(result);
        return ResponseEntity.ok(validationResponse);
    }

    @CrossOrigin
    @RequestMapping(value = "/checksum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getChecksum(@Valid @RequestBody LuhnRequestDto req, Errors errors) {
        if (errors.hasErrors() || !req.getNumber().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body("Wrong type of request");
        }
        Integer checksum = this.luhnAlgorithmService.getChecksum(req.getNumber());
        CheckSumResponse checkSumResponse = new CheckSumResponse(checksum);
        return ResponseEntity.ok(checkSumResponse);
    }


}





