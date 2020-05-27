package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * The interface Savings goal dao.
 */
public interface SavingsGoalDao {

    /**
     * Gets savings goals.
     *
     * @param accountUUID the account uuid
     * @return the savings goals
     */
    ResponseEntity<SavingsGoals> getSavingsGoals(final UUID accountUUID);

    /**
     * Add funds to savings goal response entity.
     *
     * @param accountUUID     the account uuid
     * @param savingsGoalUUID the savings goal uuid
     * @param transferUUID    the transfer uuid
     * @param topUpRequest    the top up request
     * @return the response entity
     */
    ResponseEntity<TopUpRequest> addFundsToSavingsGoal(final UUID accountUUID,
                                                       final UUID savingsGoalUUID,
                                                       final UUID transferUUID,
                                                       final TopUpRequest topUpRequest);

    /**
     * Create savings goal response entity.
     *
     * @param accountUUID        the account uuid
     * @param savingsGoalRequest the savings goal request
     * @return the response entity
     */
    ResponseEntity<SavingsGoalRequest> createSavingsGoal(final UUID accountUUID,
                                                         final SavingsGoalRequest savingsGoalRequest);
}
