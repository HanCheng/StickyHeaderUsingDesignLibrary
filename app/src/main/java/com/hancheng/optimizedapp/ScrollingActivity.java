package com.hancheng.optimizedapp;

import android.os.Bundle;
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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private CoordinatorLayout mRootView;
    private static final float SCALE_MINIMUM=0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        mRootView = (CoordinatorLayout) findViewById(R.id.root_view);
        AppBarLayout appBarLayout = (AppBarLayout) mRootView.findViewById(R.id.app_bar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) mRootView.findViewById(R.id.toolbar_layout);
        Toolbar toolbar = (Toolbar) appBarLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                collapsingToolbarLayout.setTitle("");
            }
        });

        TabLayout tabLayout = (TabLayout) appBarLayout.findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.view_pager);
        ListFragmentPagerAdapter pagerAdapter = new ListFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ScrollListFragment(), "Tab 1");
        pagerAdapter.addFragment(new ScrollListFragment(), "Tab 2");
        pagerAdapter.addFragment(new ScrollListFragment(), "Tab 3");
        pagerAdapter.addFragment(new ScrollListFragment(), "Tab 4");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    }
}
