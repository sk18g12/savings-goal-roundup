package com.stuartkellaway.starlingbankchallenge.controller;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.controllers.RoundUpController;
import com.stuartkellaway.starlingbankchallenge.entities.CurrencyAndAmount;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.services.RoundUpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoundUpControllerTest {

    private static final String TEST_CURRENCY = "GBP";
    private static final String TEST_SAVINGS_GOAL_NAME = "Test";
    private static final CurrencyAndAmount TEST_CURRENCY_AND_AMOUNT = new CurrencyAndAmount(TEST_CURRENCY, 123);
    private static final SavingsGoal TEST_SAVINGS_GOAL = new SavingsGoal(UUID.randomUUID(), TEST_SAVINGS_GOAL_NAME, TEST_CURRENCY_AND_AMOUNT);
    private static final String EXPECTED_OUTPUT = "{\"savingsGoalUid\":\"" + TEST_SAVINGS_GOAL.getSavingsGoalUid() + "\",\"name\":\"" + TEST_SAVINGS_GOAL_NAME + "\",\"totalSaved\":{\"currency\":\"" + TEST_CURRENCY + "\",\"minorUnits\":" + TEST_CURRENCY_AND_AMOUNT.getMinorUnits() + "}}";

    private MockMvc mockMvc;

    @Mock
    private UserConfiguration userConfiguration;

    @Mock
    private RoundUpService roundUpService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new RoundUpController(roundUpService, userConfiguration)).build();
        when(roundUpService.roundUpLastWeeksTransactions()).thenReturn(TEST_SAVINGS_GOAL);
    }

    @Test
    public void testRoundUp() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/roundUp")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(EXPECTED_OUTPUT, result);
    }
}
