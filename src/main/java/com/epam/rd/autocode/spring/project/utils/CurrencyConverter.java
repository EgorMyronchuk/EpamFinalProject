package com.epam.rd.autocode.spring.project.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CurrencyConverter {

    public static BigDecimal exchangeUahToUsd(BigDecimal amount){
        return amount.divide(new BigDecimal("43"), 2, RoundingMode.DOWN);
    }

}
