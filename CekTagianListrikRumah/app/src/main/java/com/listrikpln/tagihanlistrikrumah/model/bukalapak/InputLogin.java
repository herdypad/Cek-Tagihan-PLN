package com.listrikpln.tagihanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class InputLogin{

	@SerializedName("application_id")
	private int applicationId;

	@SerializedName("authenticity_token")
	private String authenticityToken;

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public void setAuthenticityToken(String authenticityToken) {
		this.authenticityToken = authenticityToken;
	}

	public int getApplicationId(){
		return applicationId;
	}

	public String getAuthenticityToken(){
		return authenticityToken;
	}
}