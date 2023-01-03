package com.listrikpln.tagiahanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class InputGetToken{

	@SerializedName("customer_number")
	private String customerNumber;


	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerNumber(){
		return customerNumber;
	}
}