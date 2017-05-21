package com.kishan.collectedransom.di.module;

import dagger.Module;
import dagger.Provides;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.inject.Singleton;

@Module
public class TwitterModule {

    @Provides
    @Singleton
    public Twitter provideTwitterInstance() {
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String accessTokenSecret = System.getenv("ACCESS_TOKEN_SECRET");

        Twitter twitter = new TwitterFactory().getInstance();

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        return twitter;
    }
}
