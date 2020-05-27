package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.dao.AccountDao;
import com.stuartkellaway.starlingbankchallenge.dao.SavingsGoalDao;
import com.stuartkellaway.starlingbankchallenge.dao.TransactionDao;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RoundUpServiceImpl implements RoundUpService {

    private static Logger log = Logger.getLogger(RoundUpServiceImpl.class.getName());

    private AccountDao accountDao;
    private SavingsGoalDao savingsGoalDao;
    private TransactionDao transactionDao;

    @Autowired
    public RoundUpServiceImpl(final AccountDao accountDao,
                              final SavingsGoalDao savingsGoalDao,
                              final TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.savingsGoalDao = savingsGoalDao;
        this.transactionDao = transactionDao;
    }

    public SavingsGoal roundUpLastWeeksTransactions() {
        return null;
    }
}
