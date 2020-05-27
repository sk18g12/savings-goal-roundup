package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.dao.TransactionDao;
import com.stuartkellaway.starlingbankchallenge.entities.Account;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static Logger log = Logger.getLogger(SavingsGoalServiceImpl.class.getName());

    private UserConfiguration userConfiguration;
    private TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(final UserConfiguration userConfiguration, final TransactionDao transactionDao) {
        this.userConfiguration = userConfiguration;
        this.transactionDao = transactionDao;
    }

    @Override
    public FeedItems getLastWeeksTransactions(Account account) {
        log.info("Calculating the 'start date' to begin searching for transactions");

        // Getting number of days to go back on for transactions from the application.properties file
        Integer defaultNumberOfTransactionDays = Integer.parseInt(userConfiguration.getDefaultNumberOfTransactionDays());

        // Creating a date based on the number provided, going back x number of days
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -defaultNumberOfTransactionDays);
        Date startDate = cal.getTime();

        ResponseEntity<FeedItems> feedItemsResponseEntity = transactionDao.getTransactions(
                account.getAccountUid(), account.getDefaultCategory(), startDate);
        FeedItems feedItems = feedItemsResponseEntity.getBody(); // WARNING: Check Status Code and not null
        return feedItems;
    }
}
