package com.medialink.rxjavabitcoin.models;

import com.google.gson.annotations.SerializedName;

public class Market {

	@SerializedName("market")
	private String market;

	@SerializedName("volume")
	private double volume;

	@SerializedName("price")
	private String price;

	private String coinName;

	public void setMarket(String market){
		this.market = market;
	}

	public String getMarket(){
		return market;
	}

	public void setVolume(double volume){
		this.volume = volume;
	}

	public double getVolume(){
		return volume;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	@Override
 	public String toString(){
		return 
			"Market{" +
			"market = '" + market + '\'' + 
			",volume = '" + volume + '\'' + 
			",price = '" + price + '\'' + 
			"}";
		}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
}