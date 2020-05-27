package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.springframework.http.ResponseEntity;

/**
 * The interface Account dao.
 */
public interface AccountDao {

    /**
     * Gets customer accounts.
     *
     * @return the customer accounts
     */
    ResponseEntity<Accounts> getCustomerAccounts();
}
