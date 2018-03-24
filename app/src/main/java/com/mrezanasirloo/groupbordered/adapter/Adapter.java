package com.mrezanasirloo.groupbordered.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * An adapter that holds a list of Groups.
 *
 *          Forked and simplified version of Lisa Wray Groupie library
 */
public class Adapter<VH extends ViewHolder> extends RecyclerView.Adapter<VH> {

    private final List<Item> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private int spanCount = 1;
    private Item lastItemForViewTypeLookup;

    public void setItems(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public int getSpanCount() {
        return spanCount;
    }

    /**
     * Optionally register an {@link OnItemClickListener} that listens to click at the root of
     * each Item where {@link Item#isClickable()} returns true
     *
     * @param onItemClickListener The click listener to set
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Optionally register an {@link OnItemLongClickListener} that listens to long click at the root of
     * each Item where {@link Item#isLongClickable()} returns true
     *
     * @param onItemLongClickListener The long click listener to set
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Item<VH> item = getItemForViewType(layoutResId);
        View itemView = inflater.inflate(layoutResId, parent, false);
        return item.createViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        // Never called (all binds go through the version with payload)
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        Item contentItem = getItem(position);
        contentItem.bind(holder, position, payloads, onItemClickListener, onItemLongClickListener);
    }

    @Override
    public void onViewRecycled(VH holder) {
        Item contentItem = holder.getItem();
        contentItem.unbind(holder);
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        Item contentItem = holder.getItem();
        return contentItem.isRecyclable();
    }

    @Override
    public int getItemViewType(int position) {
        lastItemForViewTypeLookup = getItem(position);
        if (lastItemForViewTypeLookup == null) { throw new RuntimeException("Invalid position " + position); }
        return lastItemForViewTypeLookup.getLayout();
    }

    public Item getItem(VH holder) {
        return holder.getItem();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private Item<VH> getItemForViewType(@LayoutRes int layoutResId) {
        if (lastItemForViewTypeLookup != null
                && lastItemForViewTypeLookup.getLayout() == layoutResId) {
            // We expect this to be a hit 100% of the time
            return lastItemForViewTypeLookup;
        }

        // To be extra safe in case RecyclerView implementation details change...
        for (int i = 0; i < getItemCount(); i++) {
            Item item = getItem(i);
            if (item.getLayout() == layoutResId) {
                return item;
            }
        }

        throw new IllegalStateException("Could not find model for view type: " + layoutResId);
    }

}
