package com.daily.jcy.bdmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.daily.jcy.bdmonitor.fragments.CpuFragment;
import com.daily.jcy.bdmonitor.fragments.InternetFragment;
import com.daily.jcy.bdmonitor.fragments.AppStatusFragment;
import com.daily.jcy.bdmonitor.fragments.LogFragment;
import com.daily.jcy.bdmonitor.fragments.NodeOccupyFragment;
import com.daily.jcy.bdmonitor.fragments.NodesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initNodeCheckService();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView(){
        fragments = new ArrayList<>();
        fragments.add(CpuFragment.newInstance("Cpu"));
        fragments.add(AppStatusFragment.newInstance("Io"));
        fragments.add(NodeOccupyFragment.newInstance("Memo"));
        fragments.add(NodesFragment.newInstance("Task"));
        fragments.add(InternetFragment.newInstance("Internet"));
        fragments.add(LogFragment.newInstance("Log"));
        selectItem(0);
    }

    private void selectItem(int position) {
        Fragment fragment = fragments.get(position);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this,EditPortActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cpu) {
            selectItem(0);
        } else if (id == R.id.nav_io) {
            selectItem(1);
        } else if (id == R.id.nav_memo) {
            selectItem(2);
        } else if (id == R.id.nav_task) {
            selectItem(3);
        } else if (id == R.id.nav_internet) {
            selectItem(4);
        } else if (id == R.id.nav_log) {
            selectItem(5);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 初始化节点检查服务
     */
    private void initNodeCheckService() {
        Intent intent = new Intent(this, NodeCheckService.class);
        startService(intent);
    }

}
