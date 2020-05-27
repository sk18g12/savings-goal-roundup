package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * The type Savings goal dao.
 */
@Component
public class SavingsGoalDaoImpl implements SavingsGoalDao {

    private static Logger log = Logger.getLogger(SavingsGoalDaoImpl.class.getName());

    private UserConfiguration userConfiguration;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpEntity request;

    /**
     * Instantiates a new Savings goal dao.
     *
     * @param userConfiguration the user configuration
     * @param builder           the builder
     */
    @Autowired
    public SavingsGoalDaoImpl(final UserConfiguration userConfiguration, final RestTemplateBuilder builder) {
        this.userConfiguration = userConfiguration;
        this.restTemplate = builder.build();

        // create headers with token added - with more time, we could use OAuth2RestTemplates here
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + userConfiguration.getAccessToken());
    }

    @Override
    public ResponseEntity<TopUpRequest> addFundsToSavingsGoal(final UUID accountUUID,
                                                              final UUID savingsGoalUUID,
                                                              final UUID transferUUID,
                                                              final TopUpRequest topUpRequest) {
        log.info("Making request to add funds to the Savings Goal via the Starling Bank API");
        request = new HttpEntity(topUpRequest, headers);
        return restTemplate.exchange(userConfiguration.getBaseApiUrl() + "account/" + accountUUID
                + "/savings-goals/" + savingsGoalUUID + "/add-money/" + transferUUID, HttpMethod.PUT, request, TopUpRequest.class);
    }

    @Override
    public ResponseEntity<SavingsGoalRequest> createSavingsGoal(final UUID accountUUID,
                                                                final SavingsGoalRequest savingsGoalRequest) {
        log.info("Making request to create a new Savings Goal via the Starling Bank API");
        request = new HttpEntity(savingsGoalRequest, headers);
        return restTemplate.exchange(userConfiguration.getBaseApiUrl() + "account/" + accountUUID
                + "/savings-goals", HttpMethod.PUT, request, SavingsGoalRequest.class);
    }

    @Override
    public ResponseEntity<SavingsGoals> getSavingsGoals(final UUID accountUUID) {
        log.info("Fetching all Savings Goals associated with the customers account");
        request = new HttpEntity(headers);
        return restTemplate.exchange(userConfiguration.getBaseApiUrl() + "account/" + accountUUID
                + "/savings-goals", HttpMethod.GET, request, SavingsGoals.class);
    }

}
