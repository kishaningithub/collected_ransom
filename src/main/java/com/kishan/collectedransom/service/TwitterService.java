package com.kishan.collectedransom.service;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Slf4j
public class TwitterService {
    public void sendTweet(String message) throws TwitterException {
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String accessTokenSecret = System.getenv("ACCESS_TOKEN_SECRET");

        Twitter twitter = new TwitterFactory().getInstance();

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        log.info("Sending tweet - " + message);
        twitter.updateStatus(message);
    }
}
