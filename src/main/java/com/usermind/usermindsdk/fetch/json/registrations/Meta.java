package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Meta{

	@JsonProperty("link")
	private String link;

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}
}