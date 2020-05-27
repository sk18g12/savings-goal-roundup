package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.dao.AccountDao;
import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
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

import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private static final String CURRENCY = "GBP";
    private static final Account TEST_ACCOUNT = new Account(UUID.randomUUID(), UUID.randomUUID(), CURRENCY, new Date());
    private static final Account TEST_ACCOUNT2 = new Account(UUID.randomUUID(), UUID.randomUUID(), "EUR", new Date());

    @Mock
    private AccountDao accountDao;

    @Mock
    private UserConfiguration userConfiguration;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account[] mockAccountsArray = {TEST_ACCOUNT, TEST_ACCOUNT2};

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(userConfiguration.getDefaultCurrency()).thenReturn(CURRENCY);
    }

    @Test
    public void testRetrieveCustomerAccount_shouldFilterAndReturnGBPAccountOnly() {
        Accounts mockAccounts = new Accounts(mockAccountsArray);
        ResponseEntity<Accounts> accountsResponseEntity = new ResponseEntity<>(mockAccounts, HttpStatus.OK);

        when(accountDao.getCustomerAccounts()).thenReturn(accountsResponseEntity);

        Account actualResult = accountService.getCustomerAccount();

        Assertions.assertEquals(TEST_ACCOUNT, actualResult);
    }
}