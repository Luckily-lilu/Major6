package com.ll.core.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.ll.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class BaseViewModel extends ViewModel implements LifecycleObserver {

    protected Map<String, Repository> repositoryMap;

    public BaseViewModel(){
        repositoryMap = new HashMap<>();
    }

    /**
     * 注册数据仓库
     * @param key
     * @param repository
     */
    protected  void registerRepository(String key,Repository repository){
        if (repositoryMap.containsKey(key)){
            return;
        }
        repositoryMap.put(key,repository);
    }

    /**
     * 注销数据仓库
     * @param key
     */
    protected void unRegisterRepository(String key){
        if (repositoryMap.containsKey(key)){
            repositoryMap.remove(key);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnerOwner(){
        repositoryMap.clear();
        repositoryMap = null;
    }

    protected <SubRepository extends Repository> SubRepository getRepository(String key){
        if (repositoryMap.containsKey(key)){
            return (SubRepository) repositoryMap.get(key);
        }
        return null;
    }
}
