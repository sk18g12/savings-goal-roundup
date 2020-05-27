package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SavingsGoalServiceImpl implements SavingsGoalService {

    private static Logger log = Logger.getLogger(SavingsGoalServiceImpl.class.getName());

    @Override
    public SavingsGoal getSavingsGoal(Account account) {
        return null;
    }

    @Override
    public void addFundsToSavingsGoal(Account account, SavingsGoal savingsGoal, TopUpRequest topUpRequest) {

    }

    @Override
    public void createSavingsGoal(Account account) {

    }
}
