package com.usermind.usermindsdk.fetch.json.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Attributes{

	@JsonProperty("security-token")
	private String securityToken;

	@JsonProperty("metadata")
	private Object metadata;

	@JsonProperty("private")
	private boolean jsonMemberPrivate;

	@JsonProperty("logo-url")
	private Object logoUrl;

	@JsonProperty("account-id")
	private String accountId;

	@JsonProperty("description")
	private Object description;

	@JsonProperty("start-date")
	private Object startDate;

	@JsonProperty("title")
	private String title;

	@JsonProperty("end-date")
	private Object endDate;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("location")
	private Object location;

	@JsonProperty("test-mode")
	private boolean testMode;

	@JsonProperty("banner-url")
	private Object bannerUrl;

	@JsonProperty("live")
	private boolean live;

	@JsonProperty("slug")
	private String slug;

	public void setSecurityToken(String securityToken){
		this.securityToken = securityToken;
	}

	public String getSecurityToken(){
		return securityToken;
	}

	public void setMetadata(Object metadata){
		this.metadata = metadata;
	}

	public Object getMetadata(){
		return metadata;
	}

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setLogoUrl(Object logoUrl){
		this.logoUrl = logoUrl;
	}

	public Object getLogoUrl(){
		return logoUrl;
	}

	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	public String getAccountId(){
		return accountId;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setStartDate(Object startDate){
		this.startDate = startDate;
	}

	public Object getStartDate(){
		return startDate;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setEndDate(Object endDate){
		this.endDate = endDate;
	}

	public Object getEndDate(){
		return endDate;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setLocation(Object location){
		this.location = location;
	}

	public Object getLocation(){
		return location;
	}

	public void setTestMode(boolean testMode){
		this.testMode = testMode;
	}

	public boolean isTestMode(){
		return testMode;
	}

	public void setBannerUrl(Object bannerUrl){
		this.bannerUrl = bannerUrl;
	}

	public Object getBannerUrl(){
		return bannerUrl;
	}

	public void setLive(boolean live){
		this.live = live;
	}

	public boolean isLive(){
		return live;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}