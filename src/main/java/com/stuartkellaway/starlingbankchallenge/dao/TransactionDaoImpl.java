package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class TransactionDaoImpl implements TransactionDao {

    private static Logger log = Logger.getLogger(TransactionDaoImpl.class.getName());

    private UserConfiguration userConfiguration;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpEntity request;

    public TransactionDaoImpl(final UserConfiguration userConfiguration, RestTemplateBuilder builder) {
        this.userConfiguration = userConfiguration;
        this.restTemplate = builder.build();

        // create headers with token added - with more time, we could use OAuth2RestTemplates here
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + userConfiguration.getAccessToken());

        // create request to be consumed by restTemplate
        request = new HttpEntity(headers);
    }

    @Override
    public ResponseEntity<FeedItems> getTransactions() {
        return null;
    }
}
