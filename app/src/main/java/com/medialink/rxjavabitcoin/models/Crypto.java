package com.medialink.rxjavabitcoin.models;

import com.google.gson.annotations.SerializedName;

public class Crypto {

	@SerializedName("ticker")
	private Ticker ticker;

	@SerializedName("success")
	private boolean success;

	@SerializedName("error")
	private String error;

	@SerializedName("timestamp")
	private int timestamp;

	public void setTicker(Ticker ticker){
		this.ticker = ticker;
	}

	public Ticker getTicker(){
		return ticker;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setError(String error){
		this.error = error;
	}

	public String getError(){
		return error;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Crypto{" +
			"ticker = '" + ticker + '\'' + 
			",success = '" + success + '\'' + 
			",error = '" + error + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}