package com.ll.net;

import android.os.Build;
import android.text.TextUtils;

import com.ll.common.LogUtils;
import com.ll.net.api.TokenApi;
import com.ll.net.calladapter.LiveDataCallAdapterFactory;
import com.ll.net.common.Config;
import com.ll.net.protocol.TokenRespEntity;
import com.ll.storage.core.StorageManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private final String TAG = RetrofitFactory.class.getSimpleName();
    private volatile static RetrofitFactory retrofiFactory = null;

    private Retrofit retrofit;

    private RetrofitFactory() {
        initREtrofit();
    }

    public static RetrofitFactory getInstance() {
        if (retrofiFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofiFactory == null) {
                    retrofiFactory = new RetrofitFactory();
                }
            }
        }
        return retrofiFactory;
    }

    private void initREtrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(createNetWorkInterceptor())
                .addInterceptor(tokenInterceptor())
                .addInterceptor(changeBaseUrl())
                .addNetworkInterceptor(headeerParmsInterceptor())
                .build();

        return okHttpClient;
    }

    private Interceptor changeBaseUrl() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl oldUrl = request.url();

                Request.Builder newBuilder = request.newBuilder();
                List<String> headers = request.headers(Config.NewUrlHeaderKey);
                if (headers!=null && headers.size()>0){
                    newBuilder.removeHeader(Config.NewUrlHeaderKey);
                    String headerValue = headers.get(0);
                    HttpUrl newBaseUrl = null;

                    if (headerValue.equals(Config.NewUrlHeaderValue)){
                        newBaseUrl = HttpUrl.parse(Config.TEST_SERVER_URL);
                    } else {
                        newBaseUrl = oldUrl;
                    }

                    HttpUrl newUrl = oldUrl.newBuilder()
                            .scheme(newBaseUrl.scheme())
                            .host(newBaseUrl.host())
                            .port(newBaseUrl.port())
                            .build();

                    Request newRequest = newBuilder.url(newUrl).build();

                    return chain.proceed(newRequest);
                }
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    private Interceptor headeerParmsInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("v0", Build.MANUFACTURER)
                        .addHeader("v1",Build.MODEL)
                        .addHeader("v2",Build.TYPE)
                        .addHeader("v3",""+Build.VERSION.SDK_INT)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    private Interceptor tokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //获取本地Token

                String localToken = StorageManager.getInstance().get("token");

                if (!TextUtils.isEmpty(localToken)){
                    return resetRequest(request,localToken,chain);
                }
                Response response = chain.proceed(request);

                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)){
                    String token=requestToken();
                    if (TextUtils.isEmpty(token)){
                        LogUtils.INSTANCE.e(TAG,"Error : token is null...");
                        return response;
                    }
                    StorageManager.getInstance().save("token",token);
                    return resetRequest(request,token,chain);
                }
                return response;
            }
        };
        return interceptor;
    }
    private Response resetRequest(Request request,String token,Interceptor.Chain chain){

        Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);
        Request newRequest=newBuilder.build();
        try {
            return chain.proceed(newRequest);
        } catch (IOException e) {
            LogUtils.INSTANCE.e(TAG,e.getMessage());
        }
        return null;
    }
    private String requestToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");

        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result != null && result.body() != null){
                return result.body().getAccess_token();
            }
        } catch (IOException e){
            LogUtils.INSTANCE.e(TAG,e.getMessage());
        }
        return "";
    }
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

    private boolean checkHttpCode401(Response response) {
        if (response == null) {
            return false;
        } 
        if (response.code() == 401){
            return true;
        } else {
            return false;
        }
    }

    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
