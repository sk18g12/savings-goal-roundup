package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoalRequest;
import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoals;
import com.stuartkellaway.starlingbankchallenge.entities.TopUpRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class SavingsGoalDaoImpl implements SavingsGoalDao {

    private static Logger log = Logger.getLogger(SavingsGoalDaoImpl.class.getName());

    private UserConfiguration userConfiguration;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpEntity request;

    public SavingsGoalDaoImpl(final UserConfiguration userConfiguration, final RestTemplateBuilder builder) {
        this.userConfiguration = userConfiguration;
        this.restTemplate = builder.build();

        // create headers with token added - with more time, we could use OAuth2RestTemplates here
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + userConfiguration.getAccessToken());

        // create request to be consumed by restTemplate
        request = new HttpEntity(headers);
    }

    @Override
    public ResponseEntity<SavingsGoals> getSavingsGoals() {
        return null;
    }

    @Override
    public ResponseEntity<TopUpRequest> addFundsToSavingsGoal() {
        return null;
    }

    @Override
    public ResponseEntity<SavingsGoalRequest> createSavingsGoal() {
        return null;
    }
}
