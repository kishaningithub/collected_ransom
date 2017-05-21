package com.kishan.collectedransom.health;

import com.codahale.metrics.health.HealthCheck;

import javax.inject.Inject;

public class CollectedRansomHealthCheck extends HealthCheck {

    @Inject
    public CollectedRansomHealthCheck() {

    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
