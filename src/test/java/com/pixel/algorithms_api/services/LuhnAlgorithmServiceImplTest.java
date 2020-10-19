package com.pixel.algorithms_api.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LuhnAlgorithmServiceImplTest {
    @Autowired
    private LuhnAlgorithmService luhnAlgorithmServiceImpl;
    private final String VALID_CARD_NUMBER_INPUT = "92480";
    private final String VALID_CARD_NUMBER_INPUT_2 = "924803";
    private final Integer CORRECT_CHECKSUM = 3;
    private final String INVALID_INPUT = "928403";

    @Test
    void validCardNumberInput3ShouldReturnTrue() {
        //when
        boolean isValid = luhnAlgorithmServiceImpl.validateNumber(VALID_CARD_NUMBER_INPUT_2);
        //then
        assertEquals(true, isValid);
    }

    @Test
    void validCardNumber2ShouldReturnThreeAsAChecksum() {
        //when
        Integer checksum = luhnAlgorithmServiceImpl.getChecksum(VALID_CARD_NUMBER_INPUT);
        //then
        assertEquals(CORRECT_CHECKSUM, checksum);
    }
    @Test
    void shouldReturnFalseWhenInputIsInvalid(){
        //when
        boolean isValid = luhnAlgorithmServiceImpl.validateNumber(INVALID_INPUT);
        //then
        assertEquals(false, isValid);
    }



}
