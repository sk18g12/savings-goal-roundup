package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface SavingsGoalDao {

    ResponseEntity<SavingsGoals> getSavingsGoals(final UUID accountUUID);

    ResponseEntity<TopUpRequest> addFundsToSavingsGoal(final UUID accountUUID,
                                                       final UUID savingsGoalUUID,
                                                       final UUID transferUUID,
                                                       final TopUpRequest topUpRequest);

    ResponseEntity<SavingsGoalRequest> createSavingsGoal(final UUID accountUUID,
                                                         final SavingsGoalRequest savingsGoalRequest);
}
