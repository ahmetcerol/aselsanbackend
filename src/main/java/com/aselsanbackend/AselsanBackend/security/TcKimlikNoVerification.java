package com.aselsanbackend.AselsanBackend.security;

import org.springframework.stereotype.Service;

@Service
public class TcKimlikNoVerification {

    public boolean isValidTcKimlik(String tcKimlikNo) {
        return isValidFormat(tcKimlikNo) && isValidAlgorithm(tcKimlikNo);

    }

    private boolean isValidFormat(String tcKimlikNo) {
        String regex = "^[1-9][0-9]{10}$";
        return tcKimlikNo.matches(regex);
    }

    private boolean isValidAlgorithm(String tcKimlikNo) {

        int[] digits = tcKimlikNo.chars().map(Character::getNumericValue).toArray();

        int sumEven = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int sumOdd = digits[1] + digits[3] + digits[5] + digits[7] + digits[9];

        int calculatedCheckDigit = (sumOdd * 7 - sumEven) % 10;

        return digits[10] == calculatedCheckDigit;
    }
}

