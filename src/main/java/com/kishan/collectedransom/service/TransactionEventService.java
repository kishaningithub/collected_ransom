package com.kishan.collectedransom.service;

import com.kishan.collectedransom.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import twitter4j.TwitterException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@Slf4j
public class TransactionEventService {
    public void calculateTotalBalanceAndSendTweet(Transaction transaction) throws IOException, TwitterException {
        BigInteger totalBalance = new BalanceAmountService().getTotalBalance();
        String message = getMessage(totalBalance, transaction.getTotal());
        new TwitterService().sendTweet(message);
    }

    private String getMessage(BigInteger totalBalance, BigInteger amountReceived) {
        return String.format("#WannaCry received a transaction of %s BTC. Total collection %s BTC", getBTC(amountReceived), getBTC(totalBalance));
    }

    private BigDecimal getBTC(BigInteger noOfBitCoinUnits) {
        long bitCoinUnits = 100000000L;
        int roundingScale = 8;
        return  new BigDecimal(noOfBitCoinUnits).divide(new BigDecimal(bitCoinUnits), roundingScale, RoundingMode.UNNECESSARY);
    }
}
