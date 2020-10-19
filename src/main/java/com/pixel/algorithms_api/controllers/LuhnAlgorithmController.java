package com.pixel.algorithms_api.controllers;


import com.pixel.algorithms_api.model.LuhnRequestDto;
import com.pixel.algorithms_api.services.LuhnAlgorithmService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/luhnAlgorithm")
public class LuhnAlgorithmController {

    private final LuhnAlgorithmService luhnAlgorithmService;

    public LuhnAlgorithmController(LuhnAlgorithmService luhnAlgorithmService) {
        this.luhnAlgorithmService = luhnAlgorithmService;
    }

    @RequestMapping(value = "/valid", method = RequestMethod.POST)
    public ResponseEntity checkIsValidNumber(@Valid @RequestBody LuhnRequestDto req, Errors errors) {
        if (errors.hasErrors() || !req.getNumber().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body("Wrong type of request");
        }
        boolean result = this.luhnAlgorithmService.validateNumber(req.getNumber());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/checksum", method = RequestMethod.POST)
    public ResponseEntity getChecksum(@Valid @RequestBody LuhnRequestDto req, Errors errors) {
        if (errors.hasErrors() || !req.getNumber().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body("Wrong type of request");
        }
        Integer checksum = this.luhnAlgorithmService.getChecksum(req.getNumber());
        return ResponseEntity.ok(checksum);
    }



}


