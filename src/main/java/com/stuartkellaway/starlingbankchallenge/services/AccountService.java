package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;

/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Gets customer account.
     *
     * @return the customer account
     */
    Account getCustomerAccount();
}
