package com.items.code.ui.main.activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.items.code.Activity.BaseActivity;
import com.items.code.R;
import com.items.code.Utils.ThemeUtils;
import com.items.code.ui.collect.CollectActivity;
import com.items.code.ui.main.adapter.MainFragmentPagerAdapter;
import com.items.code.ui.main.fragment.InterestingFragment;
import com.items.code.ui.main.fragment.HotFragment;
import com.items.code.ui.main.fragment.LastestFragment;
import com.items.code.ui.main.fragment.SelectFragment;
import com.items.code.ui.other.AboutActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    List<String> titlelist=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);//将Toolbar设置为Activity的导航栏。
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置侧滑菜单选择监听事件，实现onNavigationItemSelected(MenuItem item)方法
        navigationView.setNavigationItemSelectedListener(this);
        
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        titlelist.add("日报");
        titlelist.add("有趣");
        titlelist.add("热门");
        titlelist.add("精选");
        fragmentList.add(new LastestFragment());
        fragmentList.add(new InterestingFragment());
        fragmentList.add(new HotFragment());
        fragmentList.add(new SelectFragment());
        //为viewPager设置适配器
        MainFragmentPagerAdapter mainFragmentPagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager(),titlelist,fragmentList);
        viewPager.setAdapter(mainFragmentPagerAdapter);
        //为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
        //使用ViewPager的适配器
        tabLayout.setTabsFromPagerAdapter(mainFragmentPagerAdapter);

    }


    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        new AlertDialog.Builder(this).setTitle("确认退出IT资讯吗？")
                .setIcon(R.drawable.ic_error_black_24dp)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        MainActivity.this.finish();

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
//菜单监听
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mainInfo) {

            toolbar.setTitle("主页资讯");

        } else if (id == R.id.smileInfo) {
        /*    Intent intent=new Intent(MainActivity.this,CollectActivity.class);
            startActivity(intent);*/

        } else if (id == R.id.collectInfo) {
         Intent intent=new Intent(MainActivity.this,CollectActivity.class);

        } else if (id == R.id.about) {
 /*           Intent intent=new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);*/

        }
        else if (id==R.id.day_night){
            ThemeUtils.changeTheme(this);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
