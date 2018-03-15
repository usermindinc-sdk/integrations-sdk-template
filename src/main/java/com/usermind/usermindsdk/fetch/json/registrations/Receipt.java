package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Receipt{

	@JsonProperty("links")
	private Links links;

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}
}