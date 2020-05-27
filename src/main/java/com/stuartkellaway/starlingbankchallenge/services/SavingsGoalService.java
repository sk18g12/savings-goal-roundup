package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;

/**
 * The interface Savings goal service.
 */
public interface SavingsGoalService {

    /**
     * Gets savings goal.
     *
     * @param account the account
     * @return the savings goal
     */
    SavingsGoal getSavingsGoal(final Account account);

    /**
     * Add funds to savings goal.
     *
     * @param account      the account
     * @param savingsGoal  the savings goal
     * @param topUpRequest the top up request
     */
    void addFundsToSavingsGoal(final Account account, final SavingsGoal savingsGoal, final TopUpRequest topUpRequest);

    /**
     * Create savings goal.
     *
     * @param account the account
     */
    void createSavingsGoal(final Account account);

    /**
     * Create top up request top up request.
     *
     * @param amount the amount
     * @return the top up request
     */
    TopUpRequest createTopUpRequest(final Integer amount);

}
