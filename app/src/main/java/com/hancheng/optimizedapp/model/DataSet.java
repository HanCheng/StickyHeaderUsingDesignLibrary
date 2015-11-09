package com.hancheng.optimizedapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan on 11/2/15.
 */
public class DataSet {

    private static final int INITIAL_DATA_SET_LIMIT = 60;
    private static final DataSet sDataSets = new DataSet();
    private List<String> mResults = new ArrayList<>();

    public static DataSet getInstance() {
        return sDataSets;
    }

    private DataSet() {
        setDataSet(0);
    }

    public void setDataNumber(int number) {
        switch (number) {
            case 0 :
                mResults.clear();
                setDataSet(1);
                break;
            case 1:
                mResults.clear();
                setDataSet(10);
                break;
            case 2:
                mResults.clear();
                setDataSet(20);
                break;
            case 3:
                mResults.clear();
                setDataSet(0);
                break;
            default:
                mResults.clear();
                setDataSet(0);
                break;
        }
    }

    private void setDataSet(int size) {
        for (int i = 0; i < (size == 0 ? INITIAL_DATA_SET_LIMIT : size); i ++) {
            mResults.add("This is testing for row " + (i + 1));
        }
    }

    public List<String> getResults() {
        return mResults;
    }

}
