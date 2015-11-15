package com.hancheng.optimizedapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hancheng.optimizedapp.R;
import com.hancheng.optimizedapp.adapters.MyAdapter;
import com.hancheng.optimizedapp.layouts.DividerItemDecoration;

/**
 * Created by chan on 10/23/15.
 */
public class ScrollListFragment extends Fragment {

    private MyAdapter mMyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_list_view, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.lists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mMyAdapter = new MyAdapter();
        recyclerView.setAdapter(mMyAdapter);
        return rootView;
    }

    public void notifyDataSetChanged() {
        if (mMyAdapter != null && isAdded()) {
            mMyAdapter.notifyDataSetChanged();
        }
    }
}
