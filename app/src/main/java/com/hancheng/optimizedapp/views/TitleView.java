package com.hancheng.optimizedapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hancheng.optimizedapp.R;

/**
 * Created by chan on 11/18/15.
 */
public class TitleView extends LinearLayout {

    private TextView mTitleTextView;
    private TextView mSubTitleTextView;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViewElements();
    }

    private void initViewElements() {
        mTitleTextView = (TextView) this.findViewById(R.id.title_text);
        mSubTitleTextView = (TextView) this.findViewById(R.id.sub_title_text);
    }

    public void setTitleText(String titleText) {
        mTitleTextView.setText(titleText);
    }

    public void setSubTitleTextView(String subTitleText) {
        mSubTitleTextView.setText(subTitleText);
    }

    public void bindTo(String title, String subTitle) {
        hideOrSetText(mTitleTextView, title);
        hideOrSetText(mSubTitleTextView, subTitle);
    }

    private void hideOrSetText(TextView tv, String text) {
        if (text == null || text.equals(""))
            tv.setVisibility(GONE);
        else
            tv.setText(text);
    }
}
