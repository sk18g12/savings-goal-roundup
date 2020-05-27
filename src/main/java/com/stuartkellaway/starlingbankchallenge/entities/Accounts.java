package com.stuartkellaway.starlingbankchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * The type Accounts.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Value
@AllArgsConstructor
public class Accounts {

    Account[] accounts;

}
