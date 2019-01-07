package com.beksultan.e_trans.base;

import com.beksultan.e_trans.R;
import java.util.ArrayList;

public abstract class BaseRViewAdapter extends BaseSuperRViewAdapter {
    protected static final int PROGRESS_BAR_LAYOUT_ID = R.layout.adapter_loading_item;

    protected BaseRViewAdapter() {
        this.data = new ArrayList<>();
    }

    protected abstract int getLayoutResourceId();

    @Override
    public int getItemCount() {
        int count = data.size();
        if (loading) {
            count += 1;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (loading && position == getItemCount() - 1) {
            return PROGRESS_BAR_LAYOUT_ID;
        } else {
            return getLayoutResourceId();
        }
    }
}

