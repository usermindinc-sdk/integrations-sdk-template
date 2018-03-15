package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Relationships{

	@JsonProperty("tickets")
	private Tickets tickets;

	@JsonProperty("receipt")
	private Receipt receipt;

	@JsonProperty("refunds")
	private Refunds refunds;

	public void setTickets(Tickets tickets){
		this.tickets = tickets;
	}

	public Tickets getTickets(){
		return tickets;
	}

	public void setReceipt(Receipt receipt){
		this.receipt = receipt;
	}

	public Receipt getReceipt(){
		return receipt;
	}

	public void setRefunds(Refunds refunds){
		this.refunds = refunds;
	}

	public Refunds getRefunds(){
		return refunds;
	}
}