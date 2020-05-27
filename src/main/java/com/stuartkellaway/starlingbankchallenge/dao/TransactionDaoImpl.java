package com.stuartkellaway.starlingbankchallenge.dao;

import com.stuartkellaway.starlingbankchallenge.config.UserConfiguration;
import com.stuartkellaway.starlingbankchallenge.entities.FeedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class TransactionDaoImpl implements TransactionDao {

    private static Logger log = Logger.getLogger(TransactionDaoImpl.class.getName());

    private UserConfiguration userConfiguration;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpEntity request;

    @Autowired
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
    public ResponseEntity<FeedItems> getTransactions(final UUID accountUUID, final UUID categoryUUID, final Date changesSince) {
        log.info("Fetching Customer Transactions from the Starling Bank API");
        return restTemplate.exchange(userConfiguration.getBaseApiUrl() + "feed/account/" + accountUUID
                        + "/category/" + categoryUUID + "?changesSince=" + changesSince.toInstant(),
                HttpMethod.GET, request, FeedItems.class);
    }
}
