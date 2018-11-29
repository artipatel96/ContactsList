package com.example.art.contacts;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class Contacts extends AppCompatActivity {

   private Button add_button, delete_button;
    DBHelper myDB;
    ArrayList<String> listNames;
    CustomAdapter adapter;
    ListView listViewNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        myDB = new DBHelper(this);
        listNames = new ArrayList<>();
        adapter = new CustomAdapter(listNames, this);
        add_button = findViewById(R.id.add_button);
        delete_button = findViewById(R.id.delete_button);
        listViewNames = findViewById(R.id.listview);
        listViewNames.setAdapter(adapter);
        viewAList();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactDetails();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(adapter.id_location.isEmpty()){
                    makeToast("Nothing Selected");
                }
                int removed=0;
                for(int val : adapter.id_location){
                    listNames.remove(val-removed);
                    Cursor cursor = myDB.getAllData();
                    cursor.moveToPosition(val-removed);
                    myDB.deleteData(cursor.getString(0));
                    removed++;
                }

                adapter.notifyDataSetChanged();
            }
        });

        openProfile();
    }

    public void openProfile(){
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                makeToast("clicked");
                Intent intent = new Intent(Contacts.this, Contact_Profile.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }

    public void openContactDetails(){
        Intent intent = new Intent(Contacts.this, Details.class);
        startActivity(intent);
    }

    private void viewAList(){
        Cursor cursor = myDB.viewData();
        while(cursor.moveToNext()) {
            listNames.add(cursor.getString(1));
        }
        adapter.notifyDataSetChanged();

    }

    public void makeToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
