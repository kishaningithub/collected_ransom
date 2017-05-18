package com.kishan.collectedransom;

import com.kishan.collectedransom.health.CollectedRansomHealthCheck;
import com.kishan.collectedransom.controller.TransactionController;
import com.kishan.collectedransom.service.TransactionEventService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CollectedRansom extends Application<CollectedRansomConfiguration> {

    public static void main(String[] args) throws Exception {
    //    new CollectedRansom().run(args);
        new TransactionEventService().calculateTotalBalanceAndSendTweet(null);
    }


    @Override
    public void run(CollectedRansomConfiguration configuration, Environment environment) throws Exception {
        registerResources(environment);
        registerHealthChecks(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(TransactionController.class);
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register("collectedransom", new CollectedRansomHealthCheck());
    }
}
