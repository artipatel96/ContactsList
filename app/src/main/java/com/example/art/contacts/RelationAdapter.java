package com.example.art.contacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class RelationAdapter extends BaseAdapter {
    ArrayList<String> relationList;
    //TreeSet<Integer> id_location;
    Context context;

    public RelationAdapter(ArrayList<String> relationList, Context context) {
        this.relationList = relationList;
        this.context = context;
    }

    public int getCount() {
        return relationList.size(); // returns the # of items
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.relation_children,null);
        //id_location = new TreeSet<>();
        TextView Name_view = convertView.findViewById(R.id.relation_item);
        Name_view.setText(relationList.get(position));
//        CheckBox checkBox = convertView.findViewById(R.id.checkbox_item);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
//                if(isChecked){
//                    id_location.add(position);
//                }
//                else{
//                    id_location.remove(position);
//                }
//            }
//        });

        return convertView;
    }
}
