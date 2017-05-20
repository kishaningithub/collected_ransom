package com.kishan.collectedransom.controller;

import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import twitter4j.TwitterException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class TransactionController {

    @POST
    @Path("receive")
    @Timed
    public Response onReceiveBitCoins(String transaction) throws IOException, TwitterException {
        log.info("Received transaction " + transaction);
//        new TransactionEventService().calculateTotalBalanceAndSendTweet(transaction);
        return Response.ok().build();
    }
}
