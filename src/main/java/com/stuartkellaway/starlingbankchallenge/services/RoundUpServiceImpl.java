package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RoundUpServiceImpl implements RoundUpService {

    private static Logger log = Logger.getLogger(RoundUpServiceImpl.class.getName());

    private AccountService accountService;
    private SavingsGoalService savingsGoalService;
    private TransactionService transactionService;

    @Autowired
    public RoundUpServiceImpl(final AccountService accountService,
                              final SavingsGoalService savingsGoalService,
                              final TransactionService transactionService) {
        this.accountService = accountService;
        this.savingsGoalService = savingsGoalService;
        this.transactionService = transactionService;
    }

    @Override
    public SavingsGoal roundUpLastWeeksTransactions() {
        Account customerAccount = accountService.getCustomerAccount();
        SavingsGoal customerSavingsGoal = savingsGoalService.getSavingsGoal(customerAccount);

        return customerSavingsGoal;
    }
}
