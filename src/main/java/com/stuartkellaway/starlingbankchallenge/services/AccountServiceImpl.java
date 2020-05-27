package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.entities.Account;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService {

    private static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    @Override
    public Account getCustomerAccount() {
        return null;
    }
}
