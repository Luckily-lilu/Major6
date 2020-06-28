package com.ll.core.repository;

import com.ll.core.model.IModel;

public abstract class Repository<T extends IModel> {

    /**
     * 业务model
     */
    protected T mModel;

    public Repository(){
        mModel = createModel();
    }

    /**
     * 创建业务
     * @return
     */
    protected abstract T createModel();

}
