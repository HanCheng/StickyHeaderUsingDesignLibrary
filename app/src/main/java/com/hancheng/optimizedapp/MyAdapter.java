package com.hancheng.optimizedapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chan on 10/23/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mResults = {
            "This is for testing text 1",
            "This is for testing text 2",
            "This is for testing text 3",
            "This is for testing text 4",
            "This is for testing text 5",
            "This is for testing text 6",
            "This is for testing text 7",
            "This is for testing text 8",
            "This is for testing text 9",
            "This is for testing text 10",
            "This is for testing text 11",
            "This is for testing text 12",
            "This is for testing text 13",
            "This is for testing text 14",
            "This is for testing text 15",
            "This is for testing text 16",
            "This is for testing text 17",
            "This is for testing text 18",
            "This is for testing text 19",
            "This is for testing text 20",
            "This is for testing text 21",
            "This is for testing text 22",
            "This is for testing text 23",
            "This is for testing text 24",
            "This is for testing text 25",
            "This is for testing text 26",
            "This is for testing text 27",
            "This is for testing text 28",
            "This is for testing text 29",
            "This is for testing text 30",
            "This is for testing text 31",
            "This is for testing text 32",
            "This is for testing text 33",
            "This is for testing text 34",
            "This is for testing text 35",
            "This is for testing text 36",
    };

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
