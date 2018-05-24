package com.ashwini.realtimestatistics.controllers;

import com.ashwini.realtimestatistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RealtimeStatisticsController {

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Map statistics(){
        Map<String, Number> statistics =  transactionService.getStatistics();
        return statistics;
    }
}

