package com.hancheng.optimizedapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.views.TitleView;
import com.hancheng.optimizedapp.fragments.ScrollListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListFragmentPagerAdapter mPagerAdapter;
    private Toolbar mToolbar;
    private TitleView mToolBarTitleView;
    private TitleView mFloatTitleView;
    private boolean isHideToolbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        CoordinatorLayout rootView = (CoordinatorLayout) findViewById(R.id.root_view);
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final LinearLayout header = (LinearLayout) findViewById(R.id.search_header);
        final LinearLayout loadingView = (LinearLayout) header.findViewById(R.id.loading_view);
        final View sparatorThree = header.findViewById(R.id.separator_three);
        mToolBarTitleView = (TitleView) rootView.findViewById(R.id.toolbar_title_view);
        mFloatTitleView = (TitleView) rootView.findViewById(R.id.float_toolbar_title_view);
        mToolBarTitleView.bindTo("NYC - SFO", "Outbound Sat, Nov 28");
        mFloatTitleView.bindTo("NYC - SFO", "Outbound Sat, Nov 28");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(View.GONE);
                sparatorThree.setVisibility(View.GONE);
            }
        }, 3000L);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                if (percentage == 1f && isHideToolbarView) {
                    mToolBarTitleView.setVisibility(View.VISIBLE);
                    isHideToolbarView = !isHideToolbarView;
                } else if (percentage < 1f && !isHideToolbarView) {
                    mToolBarTitleView.setVisibility(View.GONE);
                    isHideToolbarView = !isHideToolbarView;
                }
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        mPagerAdapter = new ListFragmentPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new ScrollListFragment(), "LOWEST PRICE");
        mPagerAdapter.addFragment(new ScrollListFragment(), "QUICKEST");
        mPagerAdapter.addFragment(new ScrollListFragment(), "EARLIEST DEPARTURE");
        mPagerAdapter.addFragment(new ScrollListFragment(), "LATEST ARRIVAL");
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            Intent intent = new Intent(MainActivity.this, FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void notifyDataSetChanged() {
        if (mPagerAdapter != null) {
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class ListFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<String> mFragmentTitles = new ArrayList<>();
        private List<Fragment> mFragments = new ArrayList<>();

        public ListFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            for (int i = 0; i < mFragments.size(); i++) {
                ScrollListFragment scrollListFragment = (ScrollListFragment) mFragments.get(i);
                scrollListFragment.notifyDataSetChanged();
            }
        }
    }
}
