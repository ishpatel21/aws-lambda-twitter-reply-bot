package com.example.demo1.twitter;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterReplyService {

    private long dateTime = 1634226160000l;

	public static Twitter getTwitterinstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	            .setOAuthConsumerKey("add consumer key")
	            .setOAuthConsumerSecret("add consumer secret")
	            .setOAuthAccessToken("add access token")
	            .setOAuthAccessTokenSecret("add access token secret");

	    TwitterFactory tf = new TwitterFactory(cb.build());
	    return tf.getInstance();
		
	}
	
	public void replyToTweet() {
		Twitter twitter = getTwitterinstance();
		//First param of Paging() is the page number, second is the number per page (this is capped around 200 I think.
		Paging paging = new Paging(1, 30);
		try {
			List<Status> statuses = twitter.getUserTimeline("elonmusk",paging);
			statuses.sort(Comparator.comparing(Status::getCreatedAt).reversed());
			long max_dateTime = 0l;
			for(Status status : statuses) {
				if(status.getCreatedAt().getTime() > dateTime) {
					if(status.getCreatedAt().getTime() > max_dateTime) {
						max_dateTime = status.getCreatedAt().getTime();
					}					
					StatusUpdate stat= new StatusUpdate("$DOGE");
				    stat.setInReplyToStatusId(status.getInReplyToStatusId());
				    GeoLocation location= new GeoLocation(12.787, 77.7578);
				    stat.setLocation(location);
				    twitter.updateStatus(stat);
				}
			}
			this.dateTime = max_dateTime;
			System.out.println(statuses.size());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
