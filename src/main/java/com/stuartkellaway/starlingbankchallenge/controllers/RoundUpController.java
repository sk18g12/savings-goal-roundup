package com.stuartkellaway.starlingbankchallenge.controllers;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.services.RoundUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

/**
 * The type Round up controller.
 */
@Controller
public class RoundUpController {

    private static Logger log = Logger.getLogger(RoundUpController.class.getName());

    private RoundUpService roundUpService;

    /**
     * Instantiates a new Round up controller.
     *
     * @param roundUpService the round up service
     */
    @Autowired
    public RoundUpController(final RoundUpService roundUpService) {
        this.roundUpService = roundUpService;
    }

    /**
     * Exposed endpoint allowing a user to trigger the rounding-up of last weeks transactions and adding the combined round-up value to a savings goal
     *
     * @return the response entity
     */
    @GetMapping(value = "/roundUp")
    public ResponseEntity<SavingsGoal> roundUp() {
        log.info("Request received to start Round-Up");
        return new ResponseEntity<>(roundUpService.roundUpLastWeeksTransactions(), HttpStatus.OK);
    }

}
