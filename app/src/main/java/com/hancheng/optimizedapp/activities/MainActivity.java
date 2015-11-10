package com.hancheng.optimizedapp.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
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
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.fragments.ScrollListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListFragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ObjectAnimator mFadeOutAnimation, mFadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        CoordinatorLayout rootView = (CoordinatorLayout) findViewById(R.id.root_view);
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitleEnabled(false);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        createToolBarAnimation();
        final LinearLayout header = (LinearLayout) findViewById(R.id.search_header);
        final LinearLayout loadingView = (LinearLayout) header.findViewById(R.id.loading_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(View.GONE);
            }
        }, 3000L);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int toolbarPlaceHolderHeight = header.getHeight() - Math.abs(verticalOffset);
//                Log.e("MainActivity", " verticalOffset =================   " + verticalOffset);
//                Log.e("MainActivity", " header height  =================   " + header.getHeight());
//                Log.e("MainActivity", " toolbarPlaceHolderHeight =================   " + toolbarPlaceHolderHeight);
//                Log.e("MainActivity", " toolbar height =================   " + mToolbar.getHeight());
//                Log.e("MainActivity", " +++++++++++++++++++++++++++++");
//
//                if (toolbarPlaceHolderHeight <= 168) {
//                    mFadeInAnimation.start();
//                    mFadeOutAnimation.cancel();
//                }
//
//                if (toolbarPlaceHolderHeight == 1 && Math.abs(verticalOffset)) {
//                    mFadeOutAnimation.start();
//                    mFadeInAnimation.cancel();
//                }
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        mPagerAdapter = new ListFragmentPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new ScrollListFragment(), "Tab 1");
        mPagerAdapter.addFragment(new ScrollListFragment(), "Tab 2");
        mPagerAdapter.addFragment(new ScrollListFragment(), "Tab 3");
        mPagerAdapter.addFragment(new ScrollListFragment(), "Tab 4");
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
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

    private void createToolBarAnimation() {
        mFadeOutAnimation = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, 1f, 0f);
        mFadeInAnimation = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, 0f, 1f);
        mFadeOutAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mToolbar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        mFadeInAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mToolbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        mFadeInAnimation.setInterpolator(new LinearInterpolator());
        mFadeOutAnimation.setInterpolator(new LinearInterpolator());
        mFadeInAnimation.setDuration(20);
        mFadeOutAnimation.setDuration(20);
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
