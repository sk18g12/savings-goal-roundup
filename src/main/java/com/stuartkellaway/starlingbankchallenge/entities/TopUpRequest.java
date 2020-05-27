package com.stuartkellaway.starlingbankchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * The type Top up request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Value
@AllArgsConstructor
public class TopUpRequest {
    /**
     * The Amount.
     */
    CurrencyAndAmount amount;
}
