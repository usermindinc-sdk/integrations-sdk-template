package com.usermind.usermindsdk.fetch.json.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ReleaseLinks {

	@JsonProperty("related")
	private String related;

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}
}