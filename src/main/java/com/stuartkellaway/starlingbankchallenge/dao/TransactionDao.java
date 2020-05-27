package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

public interface TransactionDao {

    ResponseEntity<FeedItems> getTransactions(final UUID accountUUID, final UUID categoryUUID, final Date changesSince);
}
