package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoImpl implements AccountDao {
    @Override
    public ResponseEntity<Accounts> getCustomerAccounts() {
        return null;
    }
}
