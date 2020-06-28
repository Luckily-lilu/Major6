package com.ll.usercenter;


import com.ll.common.app.BaseAppcation;
import com.ll.storage.core.StorageManager;
import com.ll.storage.core.StorageType;

public class UserCenterApp extends BaseAppcation {
    @Override
    protected void initOtherConfig() {
        StorageManager.getInstance().init(StorageType.SP);
    }
}
