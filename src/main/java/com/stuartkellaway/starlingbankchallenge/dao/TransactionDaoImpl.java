package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionDaoImpl implements TransactionDao {
    @Override
    public ResponseEntity<FeedItems> getTransactions() {
        return null;
    }
}
