package com.usermind.usermindsdk.fetch.json.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Relationships{

	@JsonProperty("releases")
	private Releases releases;

	public void setReleases(Releases releases){
		this.releases = releases;
	}

	public Releases getReleases(){
		return releases;
	}
}