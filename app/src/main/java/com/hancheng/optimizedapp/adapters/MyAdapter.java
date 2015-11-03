package com.hancheng.optimizedapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.model.DataSet;

/**
 * Created by chan on 10/23/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mResults = new String[DataSet.getInstance().getResults().size()];

    public MyAdapter() {
        mResults = DataSet.getInstance().getResults().toArray(mResults);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.content_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = mResults[position];
        holder.mText.setText(text);
    }

    @Override
    public int getItemCount() {
        return mResults.length;
    }
}
