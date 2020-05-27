package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.CurrencyAndAmount;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

// @Disabled
@SpringBootTest
public class SavingsGoalDaoIntegrationTest {

    private static final UUID ACCOUNT_UUID = UUID.fromString("547baab0-fbf4-4d50-8de7-de7b324c11b1");
    private static final UUID SAVINGS_GOAL_UUID = UUID.fromString("59b7117f-3ec2-4afb-9eec-39df72f10023");
    private static final UUID TRANSFER_UUID = UUID.randomUUID();
    private CurrencyAndAmount currencyAndAmount = new CurrencyAndAmount("GBP", 123);
    private TopUpRequest topUpRequest = new TopUpRequest(currencyAndAmount);
    private SavingsGoalRequest savingsGoalRequest = new SavingsGoalRequest("Test", "GBP");


    // Integration Test
    // These tests will only pass with correct parameters above and a valid access token in application.properties
    // These are still useful during development even if it is just checking status codes

    @Autowired
    private SavingsGoalDao savingsGoalDao;

    @Test
    public void testGetSavingsGoal_integrationWithApi() {
        ResponseEntity<SavingsGoals> result = savingsGoalDao.getSavingsGoals(ACCOUNT_UUID);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testAddFundsToSavingsGoal_integrationWithApi() {
        ResponseEntity<TopUpRequest> result = savingsGoalDao.addFundsToSavingsGoal(ACCOUNT_UUID, SAVINGS_GOAL_UUID, TRANSFER_UUID, topUpRequest);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testCreateSavingsGoal_integrationWithApi() {
        ResponseEntity<SavingsGoalRequest> result = savingsGoalDao.createSavingsGoal(ACCOUNT_UUID, savingsGoalRequest);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
