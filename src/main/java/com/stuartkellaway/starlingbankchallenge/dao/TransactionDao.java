package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.http.ResponseEntity;

public interface TransactionDao {

    ResponseEntity<FeedItems> getTransactions();
}
