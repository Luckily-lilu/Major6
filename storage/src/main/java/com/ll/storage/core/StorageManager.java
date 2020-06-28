package com.ll.storage.core;

import com.ll.storage.core.impl.FileStorage;
import com.ll.storage.core.impl.SPStorage;

public class StorageManager {

    private static  StorageManager storageManager = new StorageManager();
    private StorageManager(){
        storage = new SPStorage();
    }
    public static StorageManager getInstance(){

        return storageManager;

    }
    private IStorage storage;

    public void init(int storageType){
        if (storageType==StorageType.SP){
            storage=new SPStorage();
        }else if (storageType==StorageType.FILE){
            storage=new FileStorage();
        }
    }

    public <T> boolean save(String key, T value) {
        return storage.save(key,value);
    }

    public <T> T get(String key) {
        return storage.get(key);
    }
}
