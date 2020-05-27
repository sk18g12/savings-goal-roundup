package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;

public interface SavingsGoalService {

    SavingsGoal getSavingsGoal(final Account account);

    void addFundsToSavingsGoal(final Account account, final SavingsGoal savingsGoal, final TopUpRequest topUpRequest);

    void createSavingsGoal(final Account account);


}
