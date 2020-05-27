package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.http.ResponseEntity;

public class SavingsGoalDaoImpl implements SavingsGoalDao {
    @Override
    public ResponseEntity<SavingsGoals> getSavingsGoals() {
        return null;
    }

    @Override
    public ResponseEntity<TopUpRequest> addFundsToSavingsGoal() {
        return null;
    }

    @Override
    public ResponseEntity<SavingsGoalRequest> createSavingsGoal() {
        return null;
    }
}
