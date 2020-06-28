package com.ll.major6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ll.account.AccountFragment;
import com.ll.finalce.FinalceFragment;
import com.ll.home.HomeFragment;
import com.ll.major6.adapter.FragmentViewPagerAdapter;
import com.ll.more.MoreFragment;
import com.ll.wight.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvBottombar;
    private ViewPager vpMain;
    private List<Fragment> fragments;
    private DrawerLayout dlMain;

    private ImageView leftImageView;
    private ImageView rightImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FinalceFragment());
        fragments.add(new AccountFragment());
        fragments.add(new MoreFragment());

        FragmentViewPagerAdapter adapter=new FragmentViewPagerAdapter(getSupportFragmentManager(),fragments);
        vpMain.setAdapter(adapter);
    }

    private void initEvent() {
        /**
         * 底部菜单切换事件
         */
        bnvBottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_bottombar_home:
                        vpMain.setCurrentItem(0);
                        setLeftRightVisible(true);
                        return true;
                    case R.id.menu_bottombar_finalce:

                        vpMain.setCurrentItem(1);
                        setLeftRightVisible(false);
                        return true;
                    case R.id.menu_bottombar_account:
                        vpMain.setCurrentItem(2);
                        setLeftRightVisible(false);
                        return true;
                    case R.id.menu_bottombar_more:
                        vpMain.setCurrentItem(3);
                        setLeftRightVisible(false);
                        return true;
                }
                return false;
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bnvBottombar.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void initView() {
        bnvBottombar = (BottomNavigationView) findViewById(R.id.bnv_bottombar);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        dlMain = (DrawerLayout) findViewById(R.id.dl_main);
        View view = findViewById(R.id.app_main_toolbar);

        TextView textView = view.findViewById(R.id.toolbar_title);
        textView.setText("梧桐金融");
        leftImageView = view.findViewById(R.id.toolbar_left_img);
        leftImageView.setImageResource(R.mipmap.user2);
        leftImageView.setVisibility(View.VISIBLE);
        leftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了左侧图片", Toast.LENGTH_SHORT).show();
            }
        });
        rightImageView = view.findViewById(R.id.toolbar_right_img);
        rightImageView.setImageResource(R.mipmap.msg2);
        rightImageView.setVisibility(View.VISIBLE);
        rightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了右侧图片", Toast.LENGTH_SHORT).show();
            }
        });
        dlMain.setScrimColor(Color.TRANSPARENT);
    }


    private void setLeftRightVisible(boolean isvisible){

        if (isvisible == true){
            leftImageView.setVisibility(View.VISIBLE);
            rightImageView.setVisibility(View.VISIBLE);
        } else {
            leftImageView.setVisibility(View.GONE);
            rightImageView.setVisibility(View.GONE);
        }
    }
}
