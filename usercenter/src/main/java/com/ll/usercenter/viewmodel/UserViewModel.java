package com.ll.usercenter.viewmodel;

import androidx.lifecycle.LiveData;

import com.ll.core.viewmodel.BaseViewModel;
import com.ll.net.calladapter.LiveDataCallAdapter;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.repository.UserRepository;

public class UserViewModel extends BaseViewModel {

    public UserEntity userEntity = new UserEntity();

    public UserViewModel(){
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository());
    }

    public LiveData<BaseRespEntity<UserEntity>> login(){
        UserRepository userRepository = getRepository(UserRepository.class.getSimpleName());

        return userRepository.login(userEntity);
    }
    public LiveData<BaseRespEntity<String>> getYZM(String string){
        UserRepository userRepository = getRepository(UserRepository.class.getSimpleName());

        return userRepository.getYZM(string);
    }
    public LiveData<BaseRespEntity<UserEntity>> register(){
        UserRepository userRepository = getRepository(UserRepository.class.getSimpleName());

        return userRepository.register(userEntity);
    }

    public LiveData<BaseRespEntity<Boolean>> update(String userid,String pwd){
        UserRepository userRepository = getRepository(UserRepository.class.getSimpleName());

        return userRepository.update(userid ,pwd);
    }
}
