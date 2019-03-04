package com.mchlroy.notekeeper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * An adapter for a RecyclerView with basic functions.
 * Works with a RecyclerView layout named recyclerview_row.xml and with a TextView of id text_view
 */
public class BasicAdapter<T> extends RecyclerView.Adapter<BasicAdapter.BaseViewHolder<T>> {
    private List<T> dataSet;
    private OnRecyclerViewItemClickListener<T> listener;

    public BasicAdapter(List<T> dataSet) {
        this.dataSet = dataSet;
    }

    public static class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        private OnRecyclerViewItemClickListener<T> listener;
        private int position;
        private T item;

        private BaseViewHolder(LinearLayout row) {
            super(row);
            textView = row.findViewById(R.id.text_view);
            row.setOnClickListener(this);
        }

        private void bind(int position, T item) {
            this.position = position;
            this.item = item;
            textView.setText(item.toString());
        }

        private void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener<T> listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.OnClick(v, position, item);
        }
    }

    @NonNull
    @Override
    public BasicAdapter.BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        LinearLayout row = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);

        BasicAdapter.BaseViewHolder<T> viewHolder = new BasicAdapter.BaseViewHolder<>(row);
        viewHolder.setOnRecyclerViewItemClickListener(listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> baseViewHolder, int i) {
        baseViewHolder.bind(i, dataSet.get(i));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setOnClickListener(OnRecyclerViewItemClickListener<T> listener) {
        this.listener = listener;
    }
}
