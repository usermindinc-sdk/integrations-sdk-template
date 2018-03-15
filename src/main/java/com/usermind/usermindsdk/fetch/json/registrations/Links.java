package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Links{

	@JsonProperty("self")
	private String self;

	public void setSelf(String self){
		this.self = self;
	}

	public String getSelf(){
		return self;
	}
}