package com.pixel.algorithms_api.services;

import org.springframework.stereotype.Service;


@Service
public class LuhnAlgorithmServiceImpl implements LuhnAlgorithmService {


    @Override
    public boolean validateNumber(String number) {
        return calculateSum(number,true) % 10 == 0;
    }


    @Override
    public Integer getChecksum(String number) {
        int sum = calculateSum(number,false);
        return sum % 10 == 0 ? sum  : 10 - sum % 10;
    }

    private int calculateSum(String number, Boolean isInValidationMode) {
        int sum = 0;
        boolean even = !isInValidationMode;
        char[] chars = number.toCharArray();
        for (int i = number.length() - 1; i >= 0; i--) {
            int temp = Character.getNumericValue(chars[i]);
            if (even) {
                temp = 2 * temp;
                if (temp >= 10)
                    temp = Character.getNumericValue(Integer.toString(temp).toCharArray()[0]) +  Character.getNumericValue(Integer.toString(temp).toCharArray()[1]);
            }
            sum += temp;
            even =!even;
        }
        return sum;
    }





}
