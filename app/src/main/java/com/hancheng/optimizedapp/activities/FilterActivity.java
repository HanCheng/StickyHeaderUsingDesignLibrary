package com.hancheng.optimizedapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.model.DataSet;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private boolean mIsChecked;
    private ArrayList<String> mResults = new ArrayList<>();

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
                mResults.clear();
                mResults.addAll(DataSet.getInstance().getResults());
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
            if (mResults.size() != 0) {
                finishWithResult();
            } else {
                Toast.makeText(this, "Error, please choose data set first", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return false;
    }

    private void finishWithResult() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(RESULTS_DATA, mResults);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(FILTER_REQUEST_CODE);
        finish();
    }
}
