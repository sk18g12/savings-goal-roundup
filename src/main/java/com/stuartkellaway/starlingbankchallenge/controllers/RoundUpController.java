package com.stuartkellaway.starlingbankchallenge.controllers;

import com.stuartkellaway.starlingbankchallenge.entities.SavingsGoal;
import com.stuartkellaway.starlingbankchallenge.services.RoundUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class RoundUpController {

    private static Logger log = Logger.getLogger(RoundUpController.class.getName());
    private RoundUpService roundUpService;

    @Autowired
    public RoundUpController(final RoundUpService roundUpService) {
        this.roundUpService = roundUpService;
    }

    /**
     * Main method to trigger the rounding-up of last weeks transactions and adding the combined round-up value to a savings goal
     */
    @GetMapping(value = "/roundUp")
    public ResponseEntity<SavingsGoal> roundUp(@RequestParam("access_token") final String access_token) {
        log.info("Request received to start Round-Up");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}