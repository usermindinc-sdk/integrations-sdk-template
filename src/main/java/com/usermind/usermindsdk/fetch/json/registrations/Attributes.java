package com.usermind.usermindsdk.fetch.json.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Attributes{

	@JsonProperty("expires-at")
	private String expiresAt;

	@JsonProperty("phone-number")
	private Object phoneNumber;

	@JsonProperty("registration-type")
	private String registrationType;

	@JsonProperty("completed-at")
	private String completedAt;

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("ip-address")
	private String ipAddress;

	@JsonProperty("total")
	private String total;

	@JsonProperty("discount-code")
	private Object discountCode;

	@JsonProperty("name")
	private String name;

	@JsonProperty("paid")
	private boolean paid;

	@JsonProperty("payment")
	private Payment payment;

	@JsonProperty("refunded")
	private boolean refunded;

	@JsonProperty("state")
	private String state;

	@JsonProperty("test-mode")
	private boolean testMode;

	@JsonProperty("billing-address")
	private BillingAddress billingAddress;

	@JsonProperty("email")
	private String email;

	public void setExpiresAt(String expiresAt){
		this.expiresAt = expiresAt;
	}

	public String getExpiresAt(){
		return expiresAt;
	}

	public void setPhoneNumber(Object phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public Object getPhoneNumber(){
		return phoneNumber;
	}

	public void setRegistrationType(String registrationType){
		this.registrationType = registrationType;
	}

	public String getRegistrationType(){
		return registrationType;
	}

	public void setCompletedAt(String completedAt){
		this.completedAt = completedAt;
	}

	public String getCompletedAt(){
		return completedAt;
	}

	public void setReference(String reference){
		this.reference = reference;
	}

	public String getReference(){
		return reference;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setDiscountCode(Object discountCode){
		this.discountCode = discountCode;
	}

	public Object getDiscountCode(){
		return discountCode;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPaid(boolean paid){
		this.paid = paid;
	}

	public boolean isPaid(){
		return paid;
	}

	public void setPayment(Payment payment){
		this.payment = payment;
	}

	public Payment getPayment(){
		return payment;
	}

	public void setRefunded(boolean refunded){
		this.refunded = refunded;
	}

	public boolean isRefunded(){
		return refunded;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setTestMode(boolean testMode){
		this.testMode = testMode;
	}

	public boolean isTestMode(){
		return testMode;
	}

	public void setBillingAddress(BillingAddress billingAddress){
		this.billingAddress = billingAddress;
	}

	public BillingAddress getBillingAddress(){
		return billingAddress;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}