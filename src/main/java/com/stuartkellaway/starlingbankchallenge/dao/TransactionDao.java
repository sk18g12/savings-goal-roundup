package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * The interface Transaction dao.
 */
public interface TransactionDao {

    /**
     * Gets transactions.
     *
     * @param accountUUID  the account uuid
     * @param categoryUUID the category uuid
     * @param changesSince the changes since
     * @return the transactions
     */
    ResponseEntity<FeedItems> getTransactions(final UUID accountUUID, final UUID categoryUUID, final Date changesSince);
}
