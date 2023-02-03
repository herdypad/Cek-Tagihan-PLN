package com.listrikpln.tagihanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class ResponTokenPln{

	@SerializedName("data")
	private Data data;

	@SerializedName("meta")
	private Meta meta;

	public void setData(Data data) {
		this.data = data;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Data getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}
}