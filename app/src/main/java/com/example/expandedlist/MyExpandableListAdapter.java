package com.example.expandedlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

class MyExpandableListAdapter extends BaseExpandableListAdapter {
    //************************Step 2***************************
    Context context;
    List<String> expListHeader;
    HashMap<String, List<String>> listDataChild;
    public MyExpandableListAdapter(){}
    public MyExpandableListAdapter(Context context, List<String> expListHeader, HashMap<String, List<String>> listDataChild)
    {
        this.context=context;
        this.expListHeader=expListHeader;
        this.listDataChild=listDataChild;
    }
    //Get number of headers
    @Override
    public int getGroupCount() {
        return this.expListHeader.size();
    }
    //return size of the current selected header's childs
    @Override
    public int getChildrenCount(int listPos) {
        return this.listDataChild.get(this.expListHeader.get(listPos)).size();
    }

    //return the Header Strings
    @Override
    public Object getGroup(int listPos) {
        return this.expListHeader.get(listPos);
    }
    //Retun the items in the header
    @Override
    public Object getChild(int listPos, int expListPos) {
        return this.listDataChild.get(this.expListHeader.get(listPos)).get(expListPos);
    }

    @Override
    public long getGroupId(int listPos) {
        return listPos;
    }

    //return the position of the child
    @Override
    public long getChildId(int listPos, int expandedListPos) {
        return expandedListPos;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPos, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle =(String)getGroup(listPos);
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_group,null);
        }
        TextView expandTextView=convertView.findViewById(R.id.expanded_list_group);
        expandTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public View getChildView(int listPos, int expandedListPos, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText =(String)getChild(listPos,expandedListPos);
      //  final int countryImage=(int)
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_item,null);
        }
        TextView expandTextView=convertView.findViewById(R.id.expanded_list_item);
        expandTextView.setText(expandedListText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
