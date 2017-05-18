package com.kishan.collectedransom.service;

import com.kishan.collectedransom.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import twitter4j.TwitterException;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
public class TransactionEventService {

    public void calculateTotalBalanceAndSendTweet(Transaction transaction) throws IOException, TwitterException {
        BigInteger totalBalance = new BalanceAmountService().getTotalBalance();
        String message = getMessage(totalBalance, transaction.getTotal());
        new TwitterService().sendTweet(message);
    }

    private String getMessage(BigInteger totalBalance, BigInteger amountReceived) {
        return String.format("#WannaCry received a transaction of %s. Total amount earned %s ", amountReceived, totalBalance);
    }
}
