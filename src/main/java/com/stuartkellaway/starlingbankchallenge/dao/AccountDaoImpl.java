package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class AccountDaoImpl implements AccountDao {

    private static Logger log = Logger.getLogger(AccountDaoImpl.class.getName());

    private RestTemplate restTemplate;

    @Autowired
    public AccountDaoImpl(final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    @Override
    public ResponseEntity<Accounts> getCustomerAccounts() {
        return null;
    }
}
