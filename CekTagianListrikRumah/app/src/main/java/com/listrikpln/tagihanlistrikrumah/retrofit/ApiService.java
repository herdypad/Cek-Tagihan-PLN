package com.listrikpln.tagihanlistrikrumah.retrofit;


import com.listrikpln.tagihanlistrikrumah.model.bukalapak.InputGetToken;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.InputLogin;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.ResponTokenLogin;
import com.listrikpln.tagihanlistrikrumah.model.bukalapak.ResponTokenPln;
import com.listrikpln.tagihanlistrikrumah.model.config.ResponseConfig;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    @GET("cek_tagihan_pln.json")
    Call<ResponseConfig> ambilConfig();




}
