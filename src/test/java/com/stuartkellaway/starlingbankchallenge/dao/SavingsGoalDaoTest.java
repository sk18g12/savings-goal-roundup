package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
public class SavingsGoalDaoTest {

    private static final UUID TEST_ACCOUNT_UUID = UUID.randomUUID();
    private static final UUID TEST_SAVINGS_GOAL_UUID = UUID.randomUUID();
    private static final UUID TEST_TRANSFER_UUID = UUID.randomUUID();
    private static final Date TEST_DATE = new Date();
    private static final CurrencyAndAmount TEST_CURRENCY_AND_AMOUNT = new CurrencyAndAmount("GBP", 123);
    private static final SavingsGoalRequest SAVINGS_GOAL_REQUEST = new SavingsGoalRequest("Test", "GBP");
    private static final SavingsGoal[] SAVINGS_GOALS_ARRAY = {};
    private static final SavingsGoals SAVINGS_GOALS = new SavingsGoals(SAVINGS_GOALS_ARRAY);
    private static final TopUpRequest TOP_UP_REQUEST = new TopUpRequest(TEST_CURRENCY_AND_AMOUNT);
    private static final ResponseEntity<SavingsGoalRequest> MOCK_SAVINGS_GOAL_REQUEST_200_RESPONSE = new ResponseEntity<>(SAVINGS_GOAL_REQUEST, HttpStatus.OK);
    private static final ResponseEntity<SavingsGoalRequest> MOCK_SAVINGS_GOAL_REQUEST_400_RESPONSE = new ResponseEntity<>(SAVINGS_GOAL_REQUEST, HttpStatus.BAD_REQUEST);
    private static final ResponseEntity<SavingsGoals> MOCK_GET_SAVINGS_GOAL_200_RESPONSE = new ResponseEntity<>(SAVINGS_GOALS, HttpStatus.OK);
    private static final ResponseEntity<SavingsGoals> MOCK_GET_SAVINGS_GOAL_400_RESPONSE = new ResponseEntity<>(SAVINGS_GOALS, HttpStatus.BAD_REQUEST);
    private static final ResponseEntity<TopUpRequest> MOCK_ADD_FUNDS_TO_SAVINGS_GOAL_200_RESPONSE = new ResponseEntity<>(TOP_UP_REQUEST, HttpStatus.OK);
    private static final ResponseEntity<TopUpRequest> MOCK_ADD_FUNDS_TO_SAVINGS_GOAL_400_RESPONSE = new ResponseEntity<>(TOP_UP_REQUEST, HttpStatus.BAD_REQUEST);


    @Mock
    RestTemplate restTemplate;

    @Mock
    UserConfiguration userConfiguration;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @InjectMocks
    SavingsGoalDaoImpl savingsGoalDao;

    @BeforeEach
    public void setUp() {
        savingsGoalDao = new SavingsGoalDaoImpl(userConfiguration, restTemplateBuilder);
        MockitoAnnotations.initMocks(this);
        Mockito.lenient().when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(userConfiguration.getBaseApiUrl()).thenReturn("test.test.com");
        Mockito.lenient().when(userConfiguration.getAccessToken()).thenReturn("test-test-test");
    }

    @Test
    public void testCreateSavingsGoal() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(SavingsGoalRequest.class))).thenReturn(MOCK_SAVINGS_GOAL_REQUEST_200_RESPONSE);

        ResponseEntity<SavingsGoalRequest> result = savingsGoalDao.createSavingsGoal(TEST_ACCOUNT_UUID, SAVINGS_GOAL_REQUEST);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.PUT), any(HttpEntity.class), eq(SavingsGoalRequest.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testCreateSavingsGoal_whenAPICallFails() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(SavingsGoalRequest.class))).thenReturn(MOCK_SAVINGS_GOAL_REQUEST_400_RESPONSE);

        ResponseEntity<SavingsGoalRequest> result = savingsGoalDao.createSavingsGoal(TEST_ACCOUNT_UUID, SAVINGS_GOAL_REQUEST);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.PUT), any(HttpEntity.class), eq(SavingsGoalRequest.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testGetSavingsGoals() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(SavingsGoals.class))).thenReturn(MOCK_GET_SAVINGS_GOAL_200_RESPONSE);

        ResponseEntity<SavingsGoals> result = savingsGoalDao.getSavingsGoals(TEST_ACCOUNT_UUID);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(SavingsGoals.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetSavingsGoals_whenAPICallFails() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(SavingsGoals.class))).thenReturn(MOCK_GET_SAVINGS_GOAL_400_RESPONSE);

        ResponseEntity<SavingsGoals> result = savingsGoalDao.getSavingsGoals(TEST_ACCOUNT_UUID);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(SavingsGoals.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testAddFundsToSavingsGoal() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(TopUpRequest.class))).thenReturn(MOCK_ADD_FUNDS_TO_SAVINGS_GOAL_200_RESPONSE);

        ResponseEntity<TopUpRequest> result = savingsGoalDao.addFundsToSavingsGoal(TEST_ACCOUNT_UUID, TEST_SAVINGS_GOAL_UUID, TEST_TRANSFER_UUID, TOP_UP_REQUEST);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.PUT), any(HttpEntity.class), eq(TopUpRequest.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testAddFundsToSavingsGoal_whenAPICallFails() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(TopUpRequest.class))).thenReturn(MOCK_ADD_FUNDS_TO_SAVINGS_GOAL_400_RESPONSE);

        ResponseEntity<TopUpRequest> result = savingsGoalDao.addFundsToSavingsGoal(TEST_ACCOUNT_UUID, TEST_SAVINGS_GOAL_UUID, TEST_TRANSFER_UUID, TOP_UP_REQUEST);


        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.PUT), any(HttpEntity.class), eq(TopUpRequest.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
