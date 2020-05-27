package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class SavingsGoalDaoImpl implements SavingsGoalDao {

    private static Logger log = Logger.getLogger(SavingsGoalDaoImpl.class.getName());

    private RestTemplate restTemplate;

    public SavingsGoalDaoImpl(final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

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
