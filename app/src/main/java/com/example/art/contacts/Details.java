package com.example.art.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends Contacts {

    Button add_person;
    EditText name;
    EditText number;
    ArrayList<String> listNames2;
    CustomAdapter adapter2;
    ListView listViewNames2;
    RelationDB relationDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //relationDB= new DBHelper(this);
        relationDB = new RelationDB(this);
        add_person = findViewById(R.id.add_b);
        name = findViewById(R.id.e_name);
        number = findViewById(R.id.e_number);

        listNames2 = new ArrayList<>();
        adapter2 = new CustomAdapter(listNames2, this);
        listViewNames2 = findViewById(R.id.listview2);
        listViewNames2.setAdapter(adapter2);
        viewAList2();

        listViewNames2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(Details.this, Contact_Profile.class);
                intent.putExtra("id",position);
                //makeToast("clicked!");
                startActivity(intent);
            }
        });

        add_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getNumber = number.getText().toString();

                if(getName.trim().equals("") || getNumber.trim().equals("")){
                    Toast.makeText(getBaseContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                addContacttoDB(getName, getNumber);

                for(int val : adapter2.id_location) {
                    Cursor cursor = myDB.getAllData();
                    cursor.moveToPosition(val);
                    //int val2 = val+1;
                    int pos = Integer.parseInt(myDB.getSize()) - 1;
                    relationDB.insertData(getName, val + "", pos + "");
                    //makeToast(myDB.getSize());
                    cursor.moveToPosition(pos);
                    relationDB.insertData(cursor.getString(1), pos + "", val + "");
                }
                openContacts();
            }
        });
    }

    public void openContacts(){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }

    public void addContacttoDB(String name, String number){
        Boolean inserted = myDB.insertData(name, number);
        if(inserted){
            Toast.makeText(getBaseContext(), "Contact Added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Couldn't Add Contact", Toast.LENGTH_SHORT).show();
        }
    }


    private void viewAList2(){
        Cursor cursor = myDB.viewData();
            while(cursor.moveToNext()){
                listNames2.add(cursor.getString(1));
            }
            adapter2.notifyDataSetChanged();
    }
}
