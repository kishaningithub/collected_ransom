package com.kishan.collectedransom.service;

import com.kishan.collectedransom.model.BitCoinAddress;
import com.kishan.collectedransom.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
public class TransactionEventService {

    private final Twitter twitter;
    private final BitCoinService bitCoinService;

    @Inject
    public TransactionEventService(Twitter twitter, BitCoinService bitCoinService) {
        this.twitter = twitter;
        this.bitCoinService = bitCoinService;
    }

    public void calculateTotalBalanceAndSendTweet(Transaction transaction) throws IOException, TwitterException {
        List<BitCoinAddress> ransomWareDestinationAddresses = asList(
                new BitCoinAddress("13AM4VW2dhxYgXeQepoHkHSQuy6NgaEb94"),
                new BitCoinAddress("12t9YDPgwueZ9NyMgw519p7AA8isjr6SMw"),
                new BitCoinAddress("115p7UMMngoj1pMvkpHijcRdfJNXj6LrLn")
        );
        BigInteger totalBalance = bitCoinService.getTotalBalance(ransomWareDestinationAddresses);
        String message = getMessage(totalBalance, transaction.getTotalReceived(ransomWareDestinationAddresses));
        log.info("Sending tweet - " + message);
        twitter.updateStatus(message);
    }

    private String getMessage(BigInteger totalBalance, BigInteger amountReceived) throws IOException {
        BigDecimal amountReceivedInBTC = bitCoinService.getBTC(amountReceived);
        BigDecimal totalBalanceInBTC = bitCoinService.getBTC(totalBalance);
        return String.format("#WannaCry received a transaction of %s BTC (%s INR). Total collection %s BTC (%s INR)",
                amountReceivedInBTC,
                bitCoinService.getINRValue(amountReceivedInBTC),
                totalBalanceInBTC,
                bitCoinService.getINRValue(totalBalanceInBTC));
    }

}
