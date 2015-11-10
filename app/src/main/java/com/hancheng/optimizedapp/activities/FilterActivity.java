package com.hancheng.optimizedapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.model.DataSet;

public class FilterActivity extends AppCompatActivity {

    private boolean mIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                invalidateOptionsMenu();
                mIsChecked = true;
                if (checkedId == R.id.checkbox_1) {
                    DataSet.getInstance().setDataNumber(0);
                } else if (checkedId == R.id.checkbox_2) {
                    DataSet.getInstance().setDataNumber(1);
                } else if (checkedId == R.id.checkbox_3) {
                    DataSet.getInstance().setDataNumber(2);
                } else if (checkedId == R.id.checkbox_4) {
                    DataSet.getInstance().setDataNumber(3);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        updateMenu(menu);
        return true;
    }

    private void updateMenu(Menu menu) {
        if (mIsChecked) {
            getMenuInflater().inflate(R.menu.filter_done, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter_done_action) {
            finish();
            return true;
        }
        return false;
    }
}
