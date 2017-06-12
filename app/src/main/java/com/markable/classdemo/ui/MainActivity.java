package com.markable.classdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.markable.classdemo.R;
import com.markable.classdemo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements View.OnClickListener{

//    ArrayList<Depart> datas = new ArrayList<>();
//    MainAdapter mAdapter;

    @Bind(R.id.text_main1)
    LinearLayout mTextMain1;
    @Bind(R.id.text_main2)
    LinearLayout mTextMain2;
    @Bind(R.id.text_main3)
    LinearLayout mTextMain3;
    @Bind(R.id.text_main4)
    LinearLayout mTextMain4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarColor(R.color.defaults);
        ButterKnife.bind(this);
        initView();
        setListener();
//        fetchData();
    }


    private void initView() {
//        setSupportActionBar(mToolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//
//        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mMainRecyclerView.setHasFixedSize(true);
//        mAdapter = new MainAdapter(getApplicationContext(), datas);
//        mMainRecyclerView.setAdapter(mAdapter);
    }

    private void setListener() {
        mTextMain1.setOnClickListener(this);
        mTextMain2.setOnClickListener(this);
        mTextMain3.setOnClickListener(this);
        mTextMain4.setOnClickListener(this);
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        mNavView.setNavigationItemSelectedListener(this);
    }

//    private void fetchData() {
//
//        RetrofitSingleton.getInstance().getAllDepartList()
//                .doOnTerminate(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                })
//                .subscribe(new Observer<ArrayList<Depart>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        try {
//                            RetrofitSingleton.disposeFailureInfo(e, getApplication());
//                        } catch (Exception e1) {
//                            Logger.e(e1.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<Depart> lists) {
//                        datas.clear();
//                        datas.addAll(lists);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
//    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//            Intent intent = new Intent(MainActivity.this, ClassListActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_main1:
                Intent intent1 = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent1);
                break;
            case R.id.text_main2:
                Intent intent2 = new Intent(MainActivity.this, ClassListActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_main3:
                Intent intent3 = new Intent(MainActivity.this, ClassListActivity.class);
                startActivity(intent3);
                break;
            case R.id.text_main4:
                Intent intent4 = new Intent(MainActivity.this, RandomClassActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}
