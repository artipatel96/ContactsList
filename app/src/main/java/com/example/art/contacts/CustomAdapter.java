package com.example.art.contacts;
import android.content.Context;
import android.support.annotation.ArrayRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.jar.Attributes;


public class CustomAdapter extends BaseAdapter {

    ArrayList<String> contactList;
    TreeSet<Integer> id_location;
    Context context;

    public CustomAdapter(ArrayList<String> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactList.size(); // returns the # of items
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.list_children,null);
        id_location = new TreeSet<>();
        TextView Name_view = convertView.findViewById(R.id.list_item);
        Name_view.setText(contactList.get(position));
        CheckBox checkBox = convertView.findViewById(R.id.checkbox_item);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    id_location.add(position);
                }
                else{
                    id_location.remove(position);
                }
            }
        });

        return convertView;
    }
}

