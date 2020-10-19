package com.pixel.algorithms_api.services;

public interface LuhnAlgorithmService {
    boolean validateNumber(String number);

    Integer getChecksum(String number);
}
