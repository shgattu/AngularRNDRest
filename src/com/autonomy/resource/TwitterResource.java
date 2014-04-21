package com.autonomy.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.autonomy.twitter.TwitterFeed;
import com.autonomy.twitter.TwitterStatus;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


@Path("/twitter")
public class TwitterResource {

	@GET
	@Path("/feed")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<TwitterFeed> twitterFeed(){
		 Twitter twitter = TwitterFactory.getSingleton();
		 List<TwitterFeed> twitterFeedList = new ArrayList<TwitterFeed>();
		 List<Status> statusList = new ArrayList<Status>();
		 try{
		
			 List<Status> statuses = twitter.getHomeTimeline();
			    System.out.println("Showing home timeline.");
			    for (Status status : statuses) {
			        System.out.println(status.getUser().getName() + ":" +
			                           status.getText());
			        statusList.add(status);
			      TwitterFeed  twitterFeed = new TwitterFeed(status);
			      twitterFeedList.add(twitterFeed);
			    }
		    
		 } catch (TwitterException te) {
		        if(401 == te.getStatusCode()){
			          System.out.println("Unable to get the access token.");
			        }else{
			          te.printStackTrace();
			        }
		    }  
		 
		 
		 return twitterFeedList;
		    //System.exit(0);
	}
	
	@POST
	@Path("/postStatus")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String twitterStatus(TwitterStatus statusValue){
	
	    Twitter twitter = TwitterFactory.getSingleton();
	    Status status = null;
		try {

	
	   
		
			status = twitter.updateStatus(statusValue.getStatusText());
		
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	    
	    
	   
		} catch (TwitterException te) {
			if(401 == te.getStatusCode()){
		          System.out.println("Unable to get the access token.");
		        }else{
		          te.printStackTrace();
		        }// TODO Auto-generated catch block
			
		} catch(Exception e){}
		 return status.getText();
	}
	
	@POST
	@Path("/twitterSearch")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<TwitterFeed> twitterSearch(String searchValue) {
		Twitter twitter = TwitterFactory.getSingleton();
		
		List<TwitterFeed> statList = new ArrayList<TwitterFeed>();
		try {
	   // List<Status> statuses = twitter.getHomeTimeline();
			Query query = new Query(searchValue);
		    QueryResult result = twitter.search(query);
		   
	    System.out.println("Showing home timeline.");
	    for (Status status : result.getTweets()) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	        TwitterFeed tfeed = new TwitterFeed(status);
	        statList.add(tfeed);
	    }
		}catch (TwitterException te) {
			if(401 == te.getStatusCode()){
		          System.out.println("Unable to get the access token.");
		        }else{
		          te.printStackTrace();
		        }// TODO Auto-generated catch block
			
		}
		return statList;
	}
	public static void main(String args[]){
		
		 //twitterStatus();
	}
}
