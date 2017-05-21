package com.kishan.collectedransom;

import com.kishan.collectedransom.di.component.CollectedRansomComponent;
import com.kishan.collectedransom.di.component.DaggerCollectedRansomComponent;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CollectedRansom extends Application<CollectedRansomConfiguration> {

    public static void main(String[] args) throws Exception {
        new CollectedRansom().run(args);
    }

    @Override
    public void run(CollectedRansomConfiguration configuration, Environment environment) throws Exception {
        CollectedRansomComponent collectedRansomComponent = DaggerCollectedRansomComponent.builder()
                .build();
        registerResources(environment, collectedRansomComponent);
        registerHealthChecks(environment, collectedRansomComponent);
    }

    private void registerResources(Environment environment, CollectedRansomComponent collectedRansomComponent) {
        environment.jersey().register(collectedRansomComponent.getTransactionController());
    }

    private void registerHealthChecks(Environment environment, CollectedRansomComponent collectedRansomComponent) {
        environment.healthChecks().register("collectedransom", collectedRansomComponent.getCollectedRansomHealthCheck());
    }
}
