package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.*;
import com.stuartkellaway.starlingbankchallenge.utils.RoundingCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class RoundUpServiceImpl implements RoundUpService {

    private static Logger log = Logger.getLogger(RoundUpServiceImpl.class.getName());

    private UserConfiguration userConfiguration;
    private AccountService accountService;
    private SavingsGoalService savingsGoalService;
    private TransactionService transactionService;
    private RoundingCalculator roundingCalculator;

    @Autowired
    public RoundUpServiceImpl(final UserConfiguration userConfiguration,
                              final AccountService accountService,
                              final SavingsGoalService savingsGoalService,
                              final TransactionService transactionService,
                              final RoundingCalculator roundingCalculator) {
        this.userConfiguration = userConfiguration;
        this.accountService = accountService;
        this.savingsGoalService = savingsGoalService;
        this.transactionService = transactionService;
        this.roundingCalculator = roundingCalculator;
    }

    @Override
    public SavingsGoal roundUpLastWeeksTransactions() {
        Account customerAccount = accountService.getCustomerAccount();
        SavingsGoal customerSavingsGoal = savingsGoalService.getSavingsGoal(customerAccount);
        FeedItems transactions = transactionService.getLastWeeksTransactions(customerAccount);

        Integer fundsToBeAddedToSavingsGoal = roundUpTransactions(transactions);
        TopUpRequest topUpRequest = savingsGoalService.createTopUpRequest(fundsToBeAddedToSavingsGoal);

        savingsGoalService.addFundsToSavingsGoal(customerAccount, customerSavingsGoal, topUpRequest);

        return validationLoggingOfRoundUp(customerAccount);
    }

    private Integer roundUpTransactions(FeedItems transactions) {

        int combinedRoundUpAmount = 0; // Initially set to 0

        for (FeedItem transaction : transactions.getFeedItems()) {
            String transactionDirection = transaction.getDirection();

            // Filtering out transactions based on their transaction direction - default "OUT" see application.properties
            if (transactionDirection.equals(userConfiguration.getDefaultTransactionDirection())) {
                CurrencyAndAmount currencyAndAmount = transaction.getAmount();
                Integer transactionAmount = currencyAndAmount.getMinorUnits(); // Original untouched transaction amount
                Integer transactionRoundUpAmount = roundingCalculator.roundUpTransaction(transactionAmount); // Amount needed to round up is returned
                combinedRoundUpAmount = combinedRoundUpAmount + transactionRoundUpAmount; // each amount needed to round up is added to a combined total
            } // else ignore (assumption that we only will work one transaction direction at one time)
        }

        // return amount to be added to savings goal
        return combinedRoundUpAmount;
    }

    private SavingsGoal validationLoggingOfRoundUp(final Account account) {
        log.info("Validation of Rounding Up...");
        SavingsGoal customerSavingsGoal = savingsGoalService.getSavingsGoal(account);
        log.info("The Savings Goal is now: £" + BigDecimal.valueOf(customerSavingsGoal.getTotalSaved().getMinorUnits()).movePointLeft(2));
        log.info("Savings Goal Round-Up Process Complete");
        return customerSavingsGoal;
    }
}
