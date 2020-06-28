package com.ll.usercenter.model.api;

import androidx.lifecycle.LiveData;

import com.ll.net.common.Config;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.net.protocol.TokenRespEntity;
import com.ll.usercenter.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

//    @Headers({Config.NewUrlHeaderKey+":"+ Config.NewUrlHeaderValue})
//    @GET("/login")
//    Call<TokenRespEntity> getTest();

//    @POST("api/User/login")
//    Call<BaseRespEntity<UserEntity>> login(@Body UserEntity userEntity);

    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Body UserEntity userEntity);

    @GET("api/User/getAuthCode")
    LiveData<BaseRespEntity<String>> getYZM(@Query("phoneNumber")String string);

    @POST("api/User/register")
    LiveData<BaseRespEntity<UserEntity>> refister(@Body UserEntity userEntity);

    @FormUrlEncoded
    @POST("api/User/modifyPwd")
    LiveData<BaseRespEntity<Boolean>> update(@Field("userid") String userid,@Field("pwd") String pwd);
}
