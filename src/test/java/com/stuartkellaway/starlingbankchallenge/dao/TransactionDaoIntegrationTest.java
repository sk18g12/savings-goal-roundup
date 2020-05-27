package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

// @Disabled
@SpringBootTest
public class TransactionDaoIntegrationTest {

    private static final UUID ACCOUNT_UUID = UUID.fromString("547baab0-fbf4-4d50-8de7-de7b324c11b1");
    private static final UUID CATEGORY_UUID = UUID.fromString("2327742d-cb8a-4a7b-80ae-289cd09f45ee");
    private Date date;

    // Integration Test
    // These tests will only pass with correct parameters above and a valid access token in application.properties
    // These are still useful during development even if it is just checking status codes

    @Autowired
    private TransactionDao transactionDao;

    @Test
    public void testGetTransactions_integrationWithApi() {
        // Fetch date a week from today
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        date = cal.getTime();

        ResponseEntity<FeedItems> result = transactionDao.getTransactions(ACCOUNT_UUID, CATEGORY_UUID, date);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
