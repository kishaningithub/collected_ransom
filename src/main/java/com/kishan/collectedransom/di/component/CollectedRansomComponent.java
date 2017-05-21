package com.kishan.collectedransom.di.component;


import com.kishan.collectedransom.controller.TransactionController;
import com.kishan.collectedransom.di.module.APIModule;
import com.kishan.collectedransom.di.module.TwitterModule;
import com.kishan.collectedransom.health.CollectedRansomHealthCheck;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        APIModule.class,
        TwitterModule.class
})
public interface CollectedRansomComponent {

    // Controllers / Resources
    TransactionController getTransactionController();

    // Health check
    CollectedRansomHealthCheck getCollectedRansomHealthCheck();
}