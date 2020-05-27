package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.http.ResponseEntity;

public interface SavingsGoalDao {

    ResponseEntity<SavingsGoals> getSavingsGoals();

    ResponseEntity<TopUpRequest> addFundsToSavingsGoal();

    ResponseEntity<SavingsGoalRequest> createSavingsGoal();
}
