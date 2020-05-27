package com.stuartkellaway.starlingbankchallenge.services;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.dao.SavingsGoalDao;
import com.stuartkellaway.starlingbankchallenge.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class SavingsGoalServiceImpl implements SavingsGoalService {

    private static Logger log = Logger.getLogger(SavingsGoalServiceImpl.class.getName());

    private UserConfiguration userConfiguration;
    private SavingsGoalDao savingsGoalDao;

    @Autowired
    public SavingsGoalServiceImpl(final UserConfiguration userConfiguration, final SavingsGoalDao savingsGoalDao) {
        this.userConfiguration = userConfiguration;
        this.savingsGoalDao = savingsGoalDao;
    }

    public SavingsGoal getSavingsGoal(final Account account) {
        log.info("Fetching Savings Goals...");
        ResponseEntity<SavingsGoals> savingsGoalsResponseEntity = savingsGoalDao.getSavingsGoals(
                account.getAccountUid());
        // WARNING: Check Status Code and not null
        return checkWhetherSavingsGoalExists(savingsGoalsResponseEntity, account);
    }

    private SavingsGoal checkWhetherSavingsGoalExists(final ResponseEntity<SavingsGoals> savingsGoalsResponseEntity, final Account account) {
        SavingsGoals savingsGoals = savingsGoalsResponseEntity.getBody();

        // WARNING: getSavingsGoalList() could be null - should check in future
        if (savingsGoals.getSavingsGoalList().length >= 1) {
            log.info("Savings Goal associated with Customer Account Found");
            return savingsGoals.getSavingsGoalList()[0];

        } else {
            log.info("No Savings Goal has been found with this account. Creating a new Savings account.");
            createSavingsGoal(account);
            ResponseEntity<SavingsGoals> savingsGoalsResponseEntityUpdatedPostSGCreation = savingsGoalDao.getSavingsGoals(
                    account.getAccountUid());
            SavingsGoals updatedSavingsGoals = savingsGoalsResponseEntityUpdatedPostSGCreation.getBody();
            return updatedSavingsGoals.getSavingsGoalList()[0];
        }
    }

    public void addFundsToSavingsGoal(final Account account, final SavingsGoal savingsGoal, final TopUpRequest topUpRequest) {
        // Create random UUID for Transfer - API require us to produce our own UUID for this
        UUID transferUUID = UUID.randomUUID();

        // Should check ResponseEntity for HttpStatus in future for failure handling
        savingsGoalDao.addFundsToSavingsGoal(
                account.getAccountUid(), savingsGoal.getSavingsGoalUid(), transferUUID, topUpRequest);

        log.info("Funds transferred successfully to " + savingsGoal.getName());
    }

    public void createSavingsGoal(final Account account) {
        SavingsGoalRequest savingsGoalRequest = new SavingsGoalRequest(
                userConfiguration.getDefaultSavingsGoalName(), userConfiguration.getDefaultCurrency());

        // Should check ResponseEntity for HttpStatus in future for failure handling
        savingsGoalDao.createSavingsGoal(account.getAccountUid(), savingsGoalRequest);

        log.info("New Savings Goal created successfully");
    }
}
