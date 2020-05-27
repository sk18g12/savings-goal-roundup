package com.stuartkellaway.starlingbankchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * The type Savings goals.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Value
@AllArgsConstructor
public class SavingsGoals {
    /**
     * The Savings goal list.
     */
    SavingsGoal[] savingsGoalList;
}
