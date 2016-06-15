package com.vnpt.hddtcustomer.views.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.vnpt.hddtcustomer.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListAdapterMenu extends BaseExpandableListAdapter{
    private Context _context;
    private List<String> _listDataHeader;
    private LinkedHashMap<String, List<String>> _listDataDetail;

    public ExpandableListAdapterMenu(Context context, List<String> listDataHeader, LinkedHashMap<String, List<String>> listDataDetail){
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataDetail = listDataDetail;
    }
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this._listDataDetail.get(this._listDataHeader.get(groupPosition)) != null){
            int count = this._listDataDetail.get(this._listDataHeader.get(groupPosition)).size();
            return count;
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataDetail.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_header_menu, null);
        }
        TextView lblListTitleMenu = (TextView) convertView.findViewById(R.id.lblListTitleMenu);
        lblListTitleMenu.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String childText = (String) getChild(groupPosition, childPosition);
        if(childText != null){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_detail_menu, null);
            }
            TextView expandedListItemDetail = (TextView) convertView.findViewById(R.id.expandedListItemDetail);
            expandedListItemDetail.setText(childText);
            return convertView;
        }
        return null;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
