package com.ll.usercenter.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ll.common.MsgUtils;
import com.ll.core.view.BaseActivity;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.R;
import com.ll.usercenter.databinding.ActivityLoginBinding;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.viewmodel.UserViewModel;
import com.ll.wight.TitleBar;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {
    private final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void initBinding() {
        binding.setVm(vm);
        binding.setCommand(this);
        View view = findViewById(R.id.login_toolbar);

        TextView textView = view.findViewById(R.id.toolbar_title);
        textView.setText("密码登录");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }

    public void loginClick(View view) {
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "" + getResourceString(R.string.user_hint_input_username), Toast.LENGTH_SHORT).show();
            showMsg(getResourceString(R.string.user_hint_input_username));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "" + getResourceString(R.string.user_hint_input_pwd), Toast.LENGTH_SHORT).show();

            showMsg(getResourceString(R.string.user_hint_input_pwd));
            return;
        }
//            LiveData<Boolean> result = vm.login();
//            if(result.getValue()){
//                Toast.makeText(this, ""+getResourceString(R.string.user_login_success), Toast.LENGTH_SHORT).show();
//
//                showMsg(getResourceString(R.string.user_login_success));
//                return;
//            }
//            else{
//                Toast.makeText(this, ""+getResourceString(R.string.user_login_failed), Toast.LENGTH_SHORT).show();
//
//                showMsg(getResourceString(R.string.user_login_failed));
//            }
//            LiveData<BaseRespEntity<UserEntity>> result = vm.login();
//            result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
//                @Override
//                public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
//                    if (userEntityBaseRespEntity!=null&&userEntityBaseRespEntity.getData()!=null){
//                        showMsg(getResourceString(R.string.user_login_success));
//                        Toast.makeText(LoginActivity.this, ""+getResourceString(R.string.user_login_success), Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(LoginActivity.this, ""+getResourceString(R.string.user_login_failed), Toast.LENGTH_SHORT).show();
//
//                        showMsg(getResourceString(R.string.user_login_failed));
//                    }
//                }
//            });

        LiveData<BaseRespEntity<UserEntity>> result = vm.login();
        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity != null && userEntityBaseRespEntity.getData() != null) {
                    showMsg(getResourceString(R.string.user_login_success));
                } else {
                    showMsg(getResourceString(R.string.user_login_failed));
                }
            }
        });
    }
    public void cbChange(View view){

    }
    public void forgetPwd(View view){
        startActivity(new Intent(LoginActivity.this,UpdatePwdActivity.class));
    }
}
