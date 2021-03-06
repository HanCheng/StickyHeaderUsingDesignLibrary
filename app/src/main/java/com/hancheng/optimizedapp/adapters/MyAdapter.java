package com.hancheng.optimizedapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.model.DataSet;

import java.util.List;

/**
 * Created by chan on 10/23/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mResults = DataSet.getInstance().getResults();

    public MyAdapter() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mText;
        public TextView mRowNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.price);
            mRowNumber = (TextView) itemView.findViewById(R.id.row_number);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = "$" + (position + 131);
        holder.mText.setText(text);
        holder.mRowNumber.setText(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }
}
