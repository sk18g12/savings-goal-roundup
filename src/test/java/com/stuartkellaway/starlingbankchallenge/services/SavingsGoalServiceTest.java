package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.dao.SavingsGoalDao;
import com.stuartkellaway.starlingbankchallenge.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SavingsGoalServiceTest {

    private static final UUID RANDOM_UUID = UUID.randomUUID();
    private static final Date DATE = new Date();
    private static final String CURRENCY = "GBP";
    private static final String SAVINGS_GOAL_NAME = "Test";
    private static final Integer AMOUNT = 102;
    private static final Account TEST_ACCOUNT = new Account(RANDOM_UUID, RANDOM_UUID, CURRENCY, DATE);
    private static final CurrencyAndAmount TEST_CURRENCY_AND_AMOUNT = new CurrencyAndAmount(CURRENCY, AMOUNT);
    private static final SavingsGoalRequest TEST_SAVINGS_GOAL_REQUEST = new SavingsGoalRequest(SAVINGS_GOAL_NAME, CURRENCY);
    private static final SavingsGoal TEST_SAVINGS_GOAL = new SavingsGoal(RANDOM_UUID, SAVINGS_GOAL_NAME, TEST_CURRENCY_AND_AMOUNT);
    private static final SavingsGoal[] TEST_SAVINGS_GOALS_ARRAY = {TEST_SAVINGS_GOAL};
    private static final SavingsGoal[] TEST_SAVINGS_GOALS_ARRAY_EMPTY = {};
    private static final SavingsGoals TEST_SAVINGS_GOALS = new SavingsGoals(TEST_SAVINGS_GOALS_ARRAY);
    private static final SavingsGoals TEST_SAVINGS_GOALS_EMPTY = new SavingsGoals(TEST_SAVINGS_GOALS_ARRAY_EMPTY);
    private static final TopUpRequest TEST_TOP_UP_REQUEST = new TopUpRequest(TEST_CURRENCY_AND_AMOUNT);

    @Mock
    private SavingsGoalDao savingsGoalsDao;
    @Mock
    private UserConfiguration userConfiguration;
    @InjectMocks
    private SavingsGoalServiceImpl savingsGoalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(userConfiguration.getDefaultSavingsGoalName()).thenReturn(SAVINGS_GOAL_NAME);
        when(userConfiguration.getDefaultCurrency()).thenReturn(CURRENCY);
    }

    @Test
    public void testCreateSavingsGoal() {
        ResponseEntity<SavingsGoalRequest> responseEntity = new ResponseEntity<>(TEST_SAVINGS_GOAL_REQUEST, HttpStatus.OK);

        when(savingsGoalsDao.createSavingsGoal(any(UUID.class), any(SavingsGoalRequest.class))).thenReturn(responseEntity);

        savingsGoalService.createSavingsGoal(TEST_ACCOUNT);

        verify(savingsGoalsDao, times(1)).createSavingsGoal(any(UUID.class), any(SavingsGoalRequest.class));
    }

    @Test
    public void testAddFundsToSavingsGoal() {
        ResponseEntity<TopUpRequest> responseEntity = new ResponseEntity<>(TEST_TOP_UP_REQUEST, HttpStatus.OK);

        when(savingsGoalsDao.addFundsToSavingsGoal(any(UUID.class), any(UUID.class), any(UUID.class),
                any(TopUpRequest.class))).thenReturn(responseEntity);

        savingsGoalService.addFundsToSavingsGoal(TEST_ACCOUNT, TEST_SAVINGS_GOAL, TEST_TOP_UP_REQUEST);

        verify(savingsGoalsDao, times(1)).addFundsToSavingsGoal(any(UUID.class),
                any(UUID.class), any(UUID.class), any(TopUpRequest.class));
    }

    @Test
    public void testGetSavingsGoal() {
        ResponseEntity<SavingsGoals> responseEntity = new ResponseEntity<>(TEST_SAVINGS_GOALS, HttpStatus.OK);

        when(savingsGoalsDao.getSavingsGoals(any(UUID.class))).thenReturn(responseEntity);

        SavingsGoal result = savingsGoalService.getSavingsGoal(TEST_ACCOUNT);

        verify(savingsGoalsDao, times(0)).createSavingsGoal(TEST_ACCOUNT.getAccountUid(), TEST_SAVINGS_GOAL_REQUEST);

        verify(savingsGoalsDao, times(1)).getSavingsGoals(any(UUID.class));

        Assertions.assertEquals(TEST_SAVINGS_GOAL, result);
    }

    @Test
    public void testGetSavingsGoal_whenSavingGoalDoesNotExist() {
        ResponseEntity<SavingsGoals> savingsGoalsResponseEntity = new ResponseEntity<>(TEST_SAVINGS_GOALS_EMPTY, HttpStatus.OK);
        ResponseEntity<SavingsGoalRequest> savingsGoalRequestResponseEntity = new ResponseEntity<>(TEST_SAVINGS_GOAL_REQUEST, HttpStatus.OK);

        when(savingsGoalsDao.getSavingsGoals(any(UUID.class))).thenReturn(savingsGoalsResponseEntity);
        when(savingsGoalsDao.createSavingsGoal(any(UUID.class), any(SavingsGoalRequest.class))).thenReturn(savingsGoalRequestResponseEntity);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> savingsGoalService.getSavingsGoal(TEST_ACCOUNT));

        verify(savingsGoalsDao, times(1)).createSavingsGoal(TEST_ACCOUNT.getAccountUid(), TEST_SAVINGS_GOAL_REQUEST);
    }
}
