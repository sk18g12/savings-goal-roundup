package com.stuartkellaway.starlingbankchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * The type Feed item.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Value
@AllArgsConstructor
public class FeedItem {
    /**
     * The Amount.
     */
    CurrencyAndAmount amount;
    /**
     * The Direction.
     */
    String direction;
}
