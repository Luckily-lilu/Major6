package com.ll.usercenter.repository;

import androidx.lifecycle.LiveData;

import com.ll.core.repository.Repository;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.model.UserModel;

public class UserRepository extends Repository<UserModel> {

    @Override
    protected UserModel createModel() {
        return new UserModel();
    }

    public LiveData<BaseRespEntity<UserEntity>> login (UserEntity userEntity){
        return mModel.login(userEntity);
    }

    public LiveData<BaseRespEntity<UserEntity>> register (UserEntity userEntity){
        return mModel.register(userEntity);
    }

    public LiveData<BaseRespEntity<String>> getYZM (String string){
        return mModel.getYZM(string);
    }
    public LiveData<BaseRespEntity<Boolean>> update (String userid ,String pwd){
        return mModel.update(userid,pwd);
    }
}
