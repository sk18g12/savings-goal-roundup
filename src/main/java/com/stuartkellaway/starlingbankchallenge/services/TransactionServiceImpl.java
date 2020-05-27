package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static Logger log = Logger.getLogger(SavingsGoalServiceImpl.class.getName());

    @Override
    public FeedItems getLastWeeksTransactions(Account account) {
        return null;
    }
}
