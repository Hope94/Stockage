package com.example.nesrine.stockage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class listBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        Intent intent=getIntent();
        ListView list=(ListView) findViewById(R.id.listView);
        DataBaseHandler db=new DataBaseHandler(this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,db.getBooks());
        list.setAdapter(arrayAdapter);
    }
}
