package com.example.art.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Contact_Profile extends Details {

    TextView getName;
    TextView getNumber;
    ListView relationNameList;
    RelationAdapter rAdapter;
    ArrayList<String> relationNameIDS;
    ArrayList<String> itemID;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__profile);
        getName.setText("");
        getName.setText("");

        id = getIntent().getExtras().getInt("id");
        relationNameList = findViewById(R.id.listview_profile);
        relationNameIDS = new ArrayList<>();
        itemID = new ArrayList<>();
        rAdapter = new RelationAdapter(relationNameIDS,this);
        relationNameList.setAdapter(rAdapter);
        getName = (TextView) findViewById(R.id.insertProfileName);
        getNumber = (TextView) findViewById(R.id.insertProfileNumber);


        Cursor cursor = myDB.getAllData();
        cursor.moveToPosition(id);
        getName.setText(cursor.getString(1));
        getNumber.setText(cursor.getString(2));

        Cursor r = relationDB.getAllData();
        while(r.moveToNext()){
            if(r.getString(3).equals(id+"")){
                cursor.moveToPosition(Integer.parseInt(r.getString(2)));
                relationNameIDS.add(cursor.getString(1));
                itemID.add(r.getString(2)); // add relatives ID aka position in myDB
            }
        }

        rAdapter.notifyDataSetChanged();

        relationNameList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(Contact_Profile.this, Contact_Profile.class);
                intent.putExtra("id",itemID.get(position));
                makeToast("ID: " + itemID.get(position));
                finish();
                startActivity(intent);
            }
        });


    }

}
