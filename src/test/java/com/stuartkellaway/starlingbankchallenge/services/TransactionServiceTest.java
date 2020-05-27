package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.dao.TransactionDao;
import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.CurrencyAndAmount;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItem;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class TransactionServiceTest {

    @Mock
    private TransactionDao transactionsDao;

    @InjectMocks
    private TransactionServiceImpl transactionsService;

    private Account account = new Account(UUID.randomUUID(), UUID.randomUUID(), "GBP", new Date());

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    // WARNING: Need to refactor to a more useful test with more time e.g. check null values, check date creation etc
    @Test
    public void testGetLastWeeksTransactions() {
        CurrencyAndAmount currencyAndAmount = new CurrencyAndAmount("GBP", 123);
        FeedItem feedItem = new FeedItem(currencyAndAmount, "OUT");
        FeedItem[] feedItemsArray = {feedItem};
        FeedItems feedItems = new FeedItems(feedItemsArray);
        ResponseEntity<FeedItems> feedItemsResponseEntity = new ResponseEntity<>(feedItems, HttpStatus.OK);
        Mockito.when(transactionsDao.getTransactions(any(UUID.class), any(UUID.class), any(Date.class))).thenReturn(feedItemsResponseEntity);

        transactionsService.getLastWeeksTransactions(account);

        Mockito.verify(transactionsDao).getTransactions(any(UUID.class), any(UUID.class), any(Date.class));
    }
}
