package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.dao.AccountDao;
import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService {

    private static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    private UserConfiguration userConfiguration;
    private AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(final AccountDao accountDao, final UserConfiguration userConfiguration) {
        this.accountDao = accountDao;
        this.userConfiguration = userConfiguration;
    }

    @Override
    public Account getCustomerAccount() {
        log.info("Fetching Customer Accounts...");
        ResponseEntity<Accounts> responseEntity = accountDao.getCustomerAccounts();

        // WARNING: These variables below could be null - checks need in future work
        Accounts accounts = responseEntity.getBody();
        Account[] accountsArray;
        accountsArray = accounts.getAccounts();

        return findDefaultCurrencyAccount(accountsArray);
    }

    // Takes the default currency from the configuration (application.properties file) and find an account based on that currency
    private Account findDefaultCurrencyAccount(final Account[] accounts) {
        Account gbpAccount = null;
        // Looping over the Accounts that the customer has to find the 'GBP' currency account
        for (Account account : accounts) {
            String currency = account.getCurrency();
            if (currency.contains(userConfiguration.getDefaultCurrency())) {
                gbpAccount = account;
                log.info("Default Currency Customer account found successfully");
            }
        }

        // WARNING: Currency account might be null - might not find an account with this currency

        return gbpAccount;
    }
}
