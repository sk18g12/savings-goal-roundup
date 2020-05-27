package com.stuartkellaway.starlingbankchallenge.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

/**
 * The type Rounding calculator.
 */
@Component
public class RoundingCalculator {

    private static Logger log = Logger.getLogger(RoundingCalculator.class.getName());

    /**
     * Round up transaction integer.
     *
     * @param transactionAmount the transaction amount
     * @return the integer
     */
// Round up a single transaction giving the amount in pence needed to round up to the nearest pound
    public Integer roundUpTransaction(final Integer transactionAmount) {
        BigDecimal bigDecimal = BigDecimal.valueOf(transactionAmount).movePointLeft(2);
        BigDecimal bigDecimalRoundUp = bigDecimal.setScale(0, RoundingMode.UP);
        Integer roundedUpValue = bigDecimalRoundUp.intValue() * 100;

        int roundUpRemainder = roundedUpValue - transactionAmount;

        log.info("Rounding up Transaction");
        log.info("Original Value - £" + bigDecimal);
        log.info("Rounded-Up Value - £" + bigDecimalRoundUp);
        log.info("£" + BigDecimal.valueOf(roundUpRemainder).movePointLeft(2) + " will be added to the savings goal");

        return roundUpRemainder;
    }
}
