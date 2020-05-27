package com.stuartkellaway.starlingbankchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.UUID;

/**
 * The type Savings goal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Value
@AllArgsConstructor
public class SavingsGoal {
    /**
     * The Savings goal uid.
     */
    UUID savingsGoalUid;
    /**
     * The Name.
     */
    String name;
    /**
     * The Total saved.
     */
    CurrencyAndAmount totalSaved;
}
