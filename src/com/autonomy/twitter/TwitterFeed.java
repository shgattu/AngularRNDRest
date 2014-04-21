package com.autonomy.twitter;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import twitter4j.ResponseList;
import twitter4j.Status;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TwitterFeed {
	
	
	public String twitterUser;
	public Date createdDate;
	public boolean isRetweeted;
	public String text;
	
	
	public String getTwitterUser() {
		return twitterUser;
	}
	public void setTwitterUser(String twitterUser) {
		this.twitterUser = twitterUser;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isRetweeted() {
		return isRetweeted;
	}
	public void setRetweeted(boolean isRetweeted) {
		this.isRetweeted = isRetweeted;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TwitterFeed(){
		
	}
	public TwitterFeed(Status feedList){
		this.createdDate= feedList.getCreatedAt();
		this.isRetweeted = feedList.isRetweeted();
		this.text= feedList.getText();
		this.twitterUser = feedList.getUser().toString();
		
	}

}
