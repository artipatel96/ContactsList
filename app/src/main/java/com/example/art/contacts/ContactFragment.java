package com.example.art.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {

    private ArrayList<String> fragContactList;
    private ArrayList<Integer> fragIds;
    private DBHelper myDB;
    private Button add;
    private Button delete;
    private ListView listView;
    private CustomAdapter fragCustomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        fragContactList = Holder.getMainContactList();
        fragIds = Holder.getMainIds();
        myDB = Holder.getMaindb();

        /*
        Toast.makeText(getContext(), l.size()+"", Toast.LENGTH_SHORT).show();

        for(String s : l)
        {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        }
        */

        listView = view.findViewById(R.id.frag_list);
        fragCustomAdapter = new CustomAdapter(fragContactList,getContext());
        listView.setAdapter(fragCustomAdapter);

        add = view.findViewById(R.id.frag_Add);
        delete = view.findViewById(R.id.frag_Delete);

        goToDetails();
        goToProfile();
        deleteContacts();

        return view;
    }

    public void goToDetails()
    {
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getContext(), "Clicked Add in Fragment", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getContext(), Details.class);
                startActivity(i);
            }
        });
    }

    public void goToProfile()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getContext(), fragIds.get(position) + "", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getContext(), Contact_Profile.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

    public void deleteContacts()
    {
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int removed = 0;
                for(int id : fragCustomAdapter.id_location)
                {
                    fragContactList.remove(id-removed);
                    Cursor cursor = myDB.getAllData();
                    cursor.moveToPosition(id-removed);
                    myDB.deleteData(cursor.getString(0));
                    removed++;

                }
                fragCustomAdapter.notifyDataSetChanged();
                fragCustomAdapter.id_location.clear();
            }
        });
    }
}
