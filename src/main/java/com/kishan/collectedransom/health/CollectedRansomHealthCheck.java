package com.kishan.collectedransom.health;

import com.codahale.metrics.health.HealthCheck;

public class CollectedRansomHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
