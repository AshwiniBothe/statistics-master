package com.ashwini.realtimestatistics.controllers;

import com.ashwini.realtimestatistics.model.Transaction;
import com.ashwini.realtimestatistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
public class RealtimeTransactionsController {

    private static final Long ONE_MINUTE_IN_MILLISECOND = 60 * 1000l;

    @Autowired
    RealTimeTransactionService realTimeTransactionService;


    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<String> transaction(@RequestBody Transaction transaction){
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        long epochInMillis = utc.toEpochSecond() * 1000;
        if(transaction.getTimestamp() < epochInMillis - ONE_MINUTE_IN_MILLISECOND){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            realTimeTransactionService.add(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }


}
