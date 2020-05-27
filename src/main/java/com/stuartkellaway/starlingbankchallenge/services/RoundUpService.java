package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;

public interface RoundUpService {

    SavingsGoal roundUpLastWeeksTransactions();
}
