package com.mchlroy.notekeeper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<NoteInfo> mDataSet;
    private View.OnClickListener mListener;

    public static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public View.OnClickListener mListener;
        public NoteViewHolder(LinearLayout row) {
            super(row);
            mTextView = row.findViewById(R.id.text_view);

            row.setOnClickListener(this);
        }

        private void setOnClickListener(View.OnClickListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }

    public NoteAdapter(List<NoteInfo> dataSet) {
        mDataSet = dataSet;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        LinearLayout row = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row, viewGroup, false);

        NoteViewHolder viewHolder = new NoteViewHolder(row);
        viewHolder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onClick(v);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        noteViewHolder.mTextView.setText(mDataSet.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mListener = listener;
    }

}
