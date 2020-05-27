package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;

public interface TransactionService {
    FeedItems getLastWeeksTransactions(Account account);
}
