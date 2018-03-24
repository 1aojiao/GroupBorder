package com.mrezanasirloo.groupbordered.listview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mrezanasirloo.groupbordered.R;

import java.util.HashMap;
import java.util.List;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-25
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> listDataHeader; // header titles
    private HashMap<String, List<String>> listDataChild;

    public ExpandableListAdapter(
            List<String> listDataHeader,
            HashMap<String, List<String>> listChildData
    ) {
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(
            int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent
    ) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_row, null);
        }

        TextView textViewItems = convertView.findViewById(R.id.textView_row);

        textViewItems.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(
            int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent
    ) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_header, null);
        }

        TextView textViewHeader = convertView.findViewById(R.id.textView_header);
        textViewHeader.setTypeface(null, Typeface.BOLD);
        textViewHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
