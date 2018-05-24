package com.ashwini.realtimestatistics.utility;

import com.ashwini.realtimestatistics.services.RealTimeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DataCleaner {

    private static final long ONE_MINUTE_IN_MILLISECOND = 60 * 1000l;

    @Autowired
    RealTimeTransactionService realTimeTransactionService;

    @Scheduled(fixedRate = 1000)
    public void cleanOldData() {
        realTimeTransactionService.deleteOldTransactions();
    }
}
