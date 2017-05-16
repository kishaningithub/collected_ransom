package com.kishan.collectedransom;

import lombok.Getter;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class RansomTweeter {
    public static void main(String[] args) {
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String accessTokenSecret = System.getenv("ACCESS_TOKEN_SECRET");

        Twitter twitter = new TwitterFactory().getInstance();

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try {
            Status status = twitter.updateStatus("Hello world  http://kishaningithub.github.io/");
            System.out.println("status.toString() = " + status.toString());

        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }
}
