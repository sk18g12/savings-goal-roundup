package com.stuartkellaway.starlingbankchallenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoundingCalculatorTest {

    // Numbers chosen based on boundary testing principles e.g. 99, 00, 01
    private static final Integer EXAMPLE_VALUE = 11399;
    private static final Integer EXPECTED_ANSWER = 1;
    private static final Integer EXAMPLE_VALUE2 = 11300;
    private static final Integer EXPECTED_ANSWER2 = 0;
    private static final Integer EXAMPLE_VALUE3 = 11301;
    private static final Integer EXPECTED_ANSWER3 = 99;

    private RoundingCalculator roundingCalculator;

    @BeforeEach
    public void setUp() {
        roundingCalculator = new RoundingCalculator();
    }

    @Test
    public void testRoundingLowerBoundary() {
        Assertions.assertEquals(EXPECTED_ANSWER, roundingCalculator.roundUpTransaction(EXAMPLE_VALUE));
    }

    @Test
    public void testRoundingOnBoundary() {
        Assertions.assertEquals(EXPECTED_ANSWER2, roundingCalculator.roundUpTransaction(EXAMPLE_VALUE2));
    }

    @Test
    public void testRoundingUpperBoundary() {
        Assertions.assertEquals(EXPECTED_ANSWER3, roundingCalculator.roundUpTransaction(EXAMPLE_VALUE3));
    }
}