package com.example.client;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  String url="http://192.168.43.71:8080/PostBook";
    private     Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void saveDB(View view){
        RequestQueue queue= Volley.newRequestQueue(this);
        book=new Book();
        EditText isbn=(EditText) findViewById(R.id.isbn);
        EditText titre=(EditText) findViewById(R.id.titre);
        EditText cat=(EditText) findViewById(R.id.category);
        book.setIsbn(isbn.getText().toString());
        book.setTitle(titre.getText().toString());
        book.setCategory(cat.getText().toString());

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this,"Good",Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,volleyError.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> map = new HashMap<String,String>();
                map.put("book",new Gson().toJson(book));
                return map;
            }
        };


     queue.add(request);

    }
}
