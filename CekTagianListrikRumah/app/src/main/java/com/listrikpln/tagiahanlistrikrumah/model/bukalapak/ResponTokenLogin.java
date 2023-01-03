package com.listrikpln.tagiahanlistrikrumah.model.bukalapak;

import com.google.gson.annotations.SerializedName;

public class ResponTokenLogin{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("refresh_token")
	private Object refreshToken;

	@SerializedName("expires_at")
	private long expiresAt;

	@SerializedName("user_id")
	private Object userId;

	@SerializedName("scope")
	private String scope;

	@SerializedName("created_at")
	private int createdAt;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private int expiresIn;

	public ResponTokenLogin(String accessToken, Object refreshToken, long expiresAt, Object userId, String scope, int createdAt, String tokenType, int expiresIn) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
		this.userId = userId;
		this.scope = scope;
		this.createdAt = createdAt;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(Object refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setExpiresAt(long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public void setUserId(Object userId) {
		this.userId = userId;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public Object getRefreshToken(){
		return refreshToken;
	}

	public long getExpiresAt(){
		return expiresAt;
	}

	public Object getUserId(){
		return userId;
	}

	public String getScope(){
		return scope;
	}

	public int getCreatedAt(){
		return createdAt;
	}

	public String getTokenType(){
		return tokenType;
	}

	public int getExpiresIn(){
		return expiresIn;
	}
}