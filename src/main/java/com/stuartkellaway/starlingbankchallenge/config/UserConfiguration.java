package com.stuartkellaway.starlingbankchallenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class UserConfiguration {

    @Value("${ACCESS_TOKEN}")
    private String accessToken;

    @Value("${BASE_API_URL}")
    private String baseApiUrl;

    @Value("${DEFAULT_SAVINGS_GOAL_NAME}")
    private String defaultSavingsGoalName;

    @Value("${DEFAULT_CURRENCY}")
    private String defaultCurrency;

    @Value("${DEFAULT_TRANSACTION_DIRECTION}")
    private String defaultTransactionDirection;
}
