package com.ll.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ll.core.view.BaseActivity;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.R;
import com.ll.usercenter.databinding.ActivityRegisterBinding;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.viewmodel.UserViewModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, UserViewModel> {

    private EditText etRegRepwd;
    private String yzm;
    private EditText etRegYzm;

    @Override
    protected void initBinding() {
        binding.setRegclick(this);
        binding.setVm(vm);
        View view = findViewById(R.id.register_toolbar);
        TextView textView = view.findViewById(R.id.toolbar_title);

        textView.setText("用户注册");

        etRegRepwd = (EditText) findViewById(R.id.et_reg_repwd);

        etRegYzm = (EditText) findViewById(R.id.et_reg_yzm);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }

    public void getYZM(View view){
        LiveData<BaseRespEntity<String>> result = vm.getYZM(vm.userEntity.getUsername());
        result.observe(this, new Observer<BaseRespEntity<String>>() {
            @Override
            public void onChanged(BaseRespEntity<String> stringBaseRespEntity) {
                if (stringBaseRespEntity != null && stringBaseRespEntity.getData() != null) {
                    showMsg(stringBaseRespEntity.getData());
                    yzm = stringBaseRespEntity.getData();
                } else {
                    showMsg("获取验证码失败");
                }
            }
        });
    }
    public void register(View view){
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if (TextUtils.isEmpty(username)) {
            showMsg(getResourceString(R.string.user_hint_input_username));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showMsg(getResourceString(R.string.user_hint_input_pwd));
            return;
        }

        if (yzm == null){
            showMsg("请获取验证码");
            return;
        } else {
            if (etRegYzm.getText().toString().equals("")){
                showMsg("验证码不能为空");
                return;
            } else{
                if (!yzm.equals(etRegYzm.getText().toString())){
                    showMsg("验证码错误");
                    return;
                }
            }
        }

        if (!vm.userEntity.getPwd().equals(etRegRepwd.getText().toString())){
            showMsg("两次密码不一致");
            return;
        }
        LiveData<BaseRespEntity<UserEntity>> result = vm.register();
        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity != null && userEntityBaseRespEntity.getData() != null) {
                    showMsg("注册成功");
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                } else {
                    showMsg(getResourceString(R.string.user_login_failed));
                }
            }
        });
    }
}
