package com.listrikpln.tagihanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("http_status")
	private int httpStatus;

	public int getHttpStatus(){
		return httpStatus;
	}
}