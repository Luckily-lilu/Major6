package com.ll.net.calladapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

public class DefaultCallAdapter <R> implements CallAdapter<R,Object> {

    Type type;

    public DefaultCallAdapter(Type t){
        type=t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
