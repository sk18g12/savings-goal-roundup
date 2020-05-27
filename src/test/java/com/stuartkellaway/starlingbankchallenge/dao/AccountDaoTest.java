package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
public class AccountDaoTest {


    private static final Account[] ACCOUNTS_ARRAY = {};
    private static final Accounts ACCOUNTS = new Accounts(ACCOUNTS_ARRAY);
    private static final ResponseEntity<Accounts> MOCK_GET_ACCOUNTS_200_RESPONSE = new ResponseEntity<>(ACCOUNTS, HttpStatus.OK);
    private static final ResponseEntity<Accounts> MOCK_GET_ACCOUNTS_400_RESPONSE = new ResponseEntity<>(ACCOUNTS, HttpStatus.BAD_REQUEST);

    @Mock
    RestTemplate restTemplate;

    @Mock
    UserConfiguration userConfiguration;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @InjectMocks
    AccountDaoImpl accountDao;

    @BeforeEach
    public void setUp() {
        accountDao = new AccountDaoImpl(userConfiguration, restTemplateBuilder);
        MockitoAnnotations.initMocks(this);
        Mockito.lenient().when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(userConfiguration.getBaseApiUrl()).thenReturn("test.test.com");
        Mockito.lenient().when(userConfiguration.getAccessToken()).thenReturn("test-test-test");
    }

    @Test
    public void testGetCustomerAccounts() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(Accounts.class))).thenReturn(MOCK_GET_ACCOUNTS_200_RESPONSE);

        ResponseEntity<Accounts> result = accountDao.getCustomerAccounts();

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(Accounts.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetCustomerAccounts_whenAPICallFails() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(Accounts.class))).thenReturn(MOCK_GET_ACCOUNTS_400_RESPONSE);

        ResponseEntity<Accounts> result = accountDao.getCustomerAccounts();

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(Accounts.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
