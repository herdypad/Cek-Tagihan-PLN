package com.listrikpln.tagihanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class BillsItem{

	@SerializedName("amount")
	private int amount;

	@SerializedName("bill_period")
	private String billPeriod;

	@SerializedName("penalty_fee")
	private int penaltyFee;

	public int getAmount(){
		return amount;
	}

	public String getBillPeriod(){
		return billPeriod;
	}

	public int getPenaltyFee(){
		return penaltyFee;
	}
}