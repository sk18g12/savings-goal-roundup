package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
    /**
     * Gets last weeks transactions.
     *
     * @param account the account
     * @return the last weeks transactions
     */
    FeedItems getLastWeeksTransactions(Account account);
}
