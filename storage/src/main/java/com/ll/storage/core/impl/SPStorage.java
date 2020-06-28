package com.ll.storage.core.impl;

import com.ll.common.LogUtils;
import com.ll.common.app.BaseAppcation;
import com.ll.storage.core.IStorage;
import com.ll.storage.utils.SharePreferenceUtils;

public class SPStorage implements IStorage {

    private final String TAG = SPStorage.class.getSimpleName();

    @Override
    public <T> boolean save(String key, T value) {
        try {
            SharePreferenceUtils.put(BaseAppcation.getAppContext(),key,value);
        }
        catch (Exception ex){
            LogUtils.INSTANCE.e(TAG,ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public <T> T get(String key) {
        T result = (T) SharePreferenceUtils.get(BaseAppcation.getAppContext(), key, "");
        return result;
    }
}
