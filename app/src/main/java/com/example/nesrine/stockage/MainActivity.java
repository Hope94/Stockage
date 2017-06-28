package com.example.nesrine.stockage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void saveOnDB(View vue){
        EditText isbn=(EditText) findViewById(R.id.isbn);
        EditText titre=(EditText) findViewById(R.id.titre);
        EditText année=(EditText) findViewById(R.id.année);
        EditText auteur=(EditText) findViewById(R.id.auteur);
        Book book=new Book();
        book.setISBN(isbn.getText().toString());
        book.setTitre(titre.getText().toString());
        book.setAnnée_edition(année.getText().toString());
        book.setAuteur(auteur.getText().toString());
        DataBaseHandler dataBaseHandler=new DataBaseHandler(MainActivity.this);
        dataBaseHandler.addBook(book);
        Intent intent=new Intent(MainActivity.this,listBook.class);
        startActivity(intent);


    }
}
