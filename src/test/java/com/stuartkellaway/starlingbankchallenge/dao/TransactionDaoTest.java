package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.CurrencyAndAmount;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItem;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
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

/**
 * The type Transaction dao test.
 */
@ExtendWith(value = MockitoExtension.class)
public class TransactionDaoTest {

    private static final UUID TEST_ACCOUNT_UUID = UUID.randomUUID();
    private static final UUID TEST_CATEGORY_UUID = UUID.randomUUID();
    private static final Date TEST_DATE = new Date();
    private static final CurrencyAndAmount TEST_CURRENCY_AND_AMOUNT = new CurrencyAndAmount("GBP", 123);
    private static final FeedItem TEST_FEED_ITEM = new FeedItem(TEST_CURRENCY_AND_AMOUNT, "OUT");
    private static final FeedItem[] TEST_FEED_ITEM_ARRAY = {TEST_FEED_ITEM};
    private static final FeedItems TEST_FEED_ITEMS = new FeedItems(TEST_FEED_ITEM_ARRAY);
    private static final ResponseEntity<FeedItems> MOCK_200_RESPONSE = new ResponseEntity<>(TEST_FEED_ITEMS, HttpStatus.OK);
    private static final ResponseEntity<FeedItems> MOCK_400_RESPONSE = new ResponseEntity<>(TEST_FEED_ITEMS, HttpStatus.BAD_REQUEST);

    /**
     * The Rest template.
     */
    @Mock
    RestTemplate restTemplate;

    /**
     * The User configuration.
     */
    @Mock
    UserConfiguration userConfiguration;

    /**
     * The Rest template builder.
     */
    @Mock
    RestTemplateBuilder restTemplateBuilder;

    /**
     * The Transaction dao.
     */
    @InjectMocks
    TransactionDaoImpl transactionDao;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        transactionDao = new TransactionDaoImpl(userConfiguration, restTemplateBuilder);
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test get transactions.
     */
    @Test
    public void testGetTransactions() {
        Mockito.lenient().when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(userConfiguration.getBaseApiUrl()).thenReturn("test.test.com");
        Mockito.lenient().when(userConfiguration.getAccessToken()).thenReturn("test-test-test");
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(FeedItems.class))).thenReturn(MOCK_200_RESPONSE);

        ResponseEntity<FeedItems> result = transactionDao.getTransactions(TEST_ACCOUNT_UUID, TEST_CATEGORY_UUID, TEST_DATE);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(FeedItems.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    /**
     * Test get transactions when api call fails.
     */
    @Test
    public void testGetTransactions_whenAPICallFails() {
        Mockito.lenient().when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(userConfiguration.getBaseApiUrl()).thenReturn("test.test.com");
        Mockito.lenient().when(userConfiguration.getAccessToken()).thenReturn("test-test-test");
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class), any(HttpEntity.class), eq(FeedItems.class))).thenReturn(MOCK_400_RESPONSE);

        ResponseEntity<FeedItems> result = transactionDao.getTransactions(TEST_ACCOUNT_UUID, TEST_CATEGORY_UUID, TEST_DATE);

        verify(restTemplate, Mockito.times(1)).exchange(any(String.class),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(FeedItems.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
