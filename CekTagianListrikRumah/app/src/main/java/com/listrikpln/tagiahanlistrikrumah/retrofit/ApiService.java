package com.listrikpln.tagiahanlistrikrumah.retrofit;


import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.InputGetToken;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.InputLogin;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.ResponTokenLogin;
import com.listrikpln.tagiahanlistrikrumah.model.bukalapak.ResponTokenPln;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String plncode = "543200673862";
    String productType= "ELECTRICITY_POSTPAID";
    String operatorName = "";

    @Headers("Content-Type: application/json")
    @POST("westeros_auth_proxies")
    Call<ResponTokenLogin> abiltokenlogin (@Body InputLogin inputbl);

    @Headers("Content-Type: application/json")
    @POST("electricities/postpaid-inquiries")
    Call<ResponTokenPln> abiltokenBl (@Query ("access_token") String token,@Body InputGetToken inputGetToken);




}
