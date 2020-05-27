package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.springframework.http.ResponseEntity;

public interface AccountDao {

    ResponseEntity<Accounts> getCustomerAccounts();
}
