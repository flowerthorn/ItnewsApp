package com.items.code.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.items.code.R;
import com.items.code.ui.main.adapter.MainFragmentPagerAdapter;
import com.items.code.ui.main.fragment.GoodselectFragment;
import com.items.code.ui.main.fragment.HotFragment;
import com.items.code.ui.main.fragment.LastestFragment;
import com.items.code.ui.main.fragment.YejieFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    List<String> titlelist=new ArrayList<String>();
    List<Fragment> fragmentList=new ArrayList<Fragment>();
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
        titlelist.add("最新");
        titlelist.add("精选");
        titlelist.add("热门");
        titlelist.add("业界");
        fragmentList.add(new LastestFragment());
        fragmentList.add(new GoodselectFragment());
        fragmentList.add(new HotFragment());
        fragmentList.add(new YejieFragment());

        //为viewPager设置适配器
        MainFragmentPagerAdapter mainFragmentPagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager(),titlelist,fragmentList);
        viewPager.setAdapter(mainFragmentPagerAdapter);
        //为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
        //使用ViewPager的适配器
        tabLayout.setTabsFromPagerAdapter(mainFragmentPagerAdapter);

      /*  for(int i=0;i<titlelist.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titlelist.get(i)));}
        for (int i=0;i<titlelist.size();i++) {
        }
*/




    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
//菜单监听
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mainInfo) {
            //  action
            toolbar.setTitle("IT资讯");



        } else if (id == R.id.specialInfo) {
            toolbar.setTitle("专栏");

        } else if (id == R.id.collectInfo) {


        } else if (id == R.id.day_night) {

        }else if (id == R.id.settings) {

        } else if (id == R.id.about) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
