package com.ll.usercenter.model;



import androidx.lifecycle.LiveData;

import com.ll.core.model.IModel;
import com.ll.net.RetrofitFactory;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.model.api.UserApi;

public class UserModel implements IModel {
    private final String TAG = UserModel.class.getSimpleName();

    public LiveData<BaseRespEntity<UserEntity>> login(final UserEntity userEntity){
//        LogUtils.INSTANCE.e(TAG,"userEntity: username -- "+userEntity.getUsername()+" pwd --"+userEntity.getPwd());
//
//        MutableLiveData<Boolean> result = new MutableLiveData<>();
//        if (Looper.getMainLooper().getThread() == Thread.currentThread()){
//            result.setValue(false);
//        } else {
//            result.postValue(false);
//        }
//        return result;

//        final MutableLiveData<BaseRespEntity<UserEntity>> result = new MutableLiveData<>();
//
//        Flowable.create(new FlowableOnSubscribe<BaseRespEntity<UserEntity>>() {
//            @Override
//            public void subscribe(final FlowableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
//
//                UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
//
//                Call<BaseRespEntity<UserEntity>> call = userApi.login(userEntity);
//                call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
//                    @Override
//                    public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
//                        if (response.body().getCode() == -1) {
//                            emitter.onError(new RuntimeException("用户登录失败"));
//                            return;
//
//                        }
//                        emitter.onNext(response.body());
//                        emitter.onComplete();
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
//                        emitter.onError(t);
//                    }
//                });
//            }
//        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseRespEntity<UserEntity>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
//                        result.postValue(userEntityBaseRespEntity);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        result.postValue(null);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//        return result;
        final UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

        LiveData<BaseRespEntity<UserEntity>> result = userApi.login(userEntity);

        return result;

    }

    public LiveData<BaseRespEntity<UserEntity>> register(final UserEntity userEntity){
        final UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

        LiveData<BaseRespEntity<UserEntity>> result = userApi.refister(userEntity);

        return result;
    }
    public LiveData<BaseRespEntity<String>> getYZM(final String string){
        final UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

        LiveData<BaseRespEntity<String>> result = userApi.getYZM(string);

        return result;
    }
    public LiveData<BaseRespEntity<Boolean>> update(final String userid,String pwd){
        final UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

        LiveData<BaseRespEntity<Boolean>> result = userApi.update(userid,pwd);

        return result;
    }
}
