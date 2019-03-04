package com.mchlroy.notekeeper;

import android.view.View;

public abstract class OnRecyclerViewItemClickListener<T> {
    public abstract void OnClick(View view, int i, T item);
}
