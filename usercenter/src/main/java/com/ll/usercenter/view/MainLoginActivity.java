package com.ll.usercenter.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ll.core.view.BaseActivity;
import com.ll.usercenter.R;
import com.ll.usercenter.databinding.ActivityMainLogin2Binding;
import com.ll.usercenter.viewmodel.UserViewModel;

public class MainLoginActivity extends BaseActivity<ActivityMainLogin2Binding, UserViewModel> {

    @Override
    protected void initBinding() {

        View view = findViewById(R.id.mainlogin_toolbar);
        TextView textView = view.findViewById(R.id.toolbar_title);

        textView.setText("登录");

        binding.setClick(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_login2;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }


    public void onClickReg(View view){
        startActivity(new Intent(MainLoginActivity.this,RegisterActivity.class));
    }

    public void onClickPwdLogin(View view){
        startActivity(new Intent(MainLoginActivity.this,LoginActivity.class));

    }

    public void onClickQQ(View view){
        showMsg("QQ登录成功");
    }
    public void onClickWX(View view){
        showMsg("微信登录成功");
    }
    public void onClickWB(View view){
        showMsg("微博登陆成功");
    }
}
