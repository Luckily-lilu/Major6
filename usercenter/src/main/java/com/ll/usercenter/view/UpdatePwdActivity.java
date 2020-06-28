package com.ll.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ll.core.view.BaseActivity;
import com.ll.net.protocol.BaseRespEntity;
import com.ll.usercenter.R;
import com.ll.usercenter.databinding.ActivityUpdatePwdBinding;
import com.ll.usercenter.entity.UserEntity;
import com.ll.usercenter.viewmodel.UserViewModel;


public class UpdatePwdActivity extends BaseActivity<ActivityUpdatePwdBinding, UserViewModel> {

    @Override
    protected void initBinding() {
        binding.setClick(this);
        binding.setVm(vm);

        View view = findViewById(R.id.updatepwd_toolbar);
        TextView textView = view.findViewById(R.id.toolbar_title);

        textView.setText("修改密码");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }
    public void update(View view){
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        LiveData<BaseRespEntity<Boolean>> result = vm.update("0",pwd);
        result.observe(this, new Observer<BaseRespEntity<Boolean>>() {
            @Override
            public void onChanged(BaseRespEntity<Boolean> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity != null && userEntityBaseRespEntity.getData() != false) {
                    startActivity(new Intent(UpdatePwdActivity.this,LoginActivity.class));

                    showMsg("修改成功");
                } else {
                    showMsg("修改失败");
                }
            }
        });
    }
    public void getYZM(View view){

    }
}
