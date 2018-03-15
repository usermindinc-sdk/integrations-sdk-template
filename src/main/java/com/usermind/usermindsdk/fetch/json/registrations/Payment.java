package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Payment{

	@JsonProperty("reference")
	private Object reference;

	@JsonProperty("type")
	private String type;

	public void setReference(Object reference){
		this.reference = reference;
	}

	public Object getReference(){
		return reference;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}