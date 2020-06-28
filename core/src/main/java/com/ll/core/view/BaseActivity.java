package com.ll.core.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ll.core.viewmodel.BaseViewModel;

public abstract class BaseActivity <Binding extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected Binding binding;
    protected VM vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        vm = createVM();
        initBinding();
    }

    /**
     * 初始化绑定
     */
    protected abstract void initBinding();

    /**
     * 设置布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 创建VM
     * @return
     */
    protected abstract VM createVM();

    /**
     * 提示信息
     * @param msg
     */
    protected void showMsg(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 全球化
     * @param strId
     * @return
     */
    protected String getResourceString(int strId){
        return getResources().getString(strId);
    }
}
