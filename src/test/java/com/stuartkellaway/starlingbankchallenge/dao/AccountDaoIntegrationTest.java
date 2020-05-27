package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// @Disabled
@SpringBootTest
public class AccountDaoIntegrationTest {

    // Integration Test
    // These tests will only pass with  a valid access token in application.properties
    // These are still useful during development even if it is just checking status codes

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testGetCustomerAccounts_integrationWithApi() {
        ResponseEntity<Accounts> result = accountDao.getCustomerAccounts();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
