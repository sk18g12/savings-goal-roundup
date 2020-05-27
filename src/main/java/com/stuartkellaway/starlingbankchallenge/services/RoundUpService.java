package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;

/**
 * The interface Round up service.
 */
public interface RoundUpService {

    /**
     * Round up last weeks transactions savings goal.
     *
     * @return the savings goal
     */
    SavingsGoal roundUpLastWeeksTransactions();
}
