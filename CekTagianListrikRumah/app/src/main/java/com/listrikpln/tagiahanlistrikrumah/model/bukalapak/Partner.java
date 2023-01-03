package com.listrikpln.tagiahanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class Partner{

	@SerializedName("name")
	private String name;

	public String getName(){
		return name;
	}
}