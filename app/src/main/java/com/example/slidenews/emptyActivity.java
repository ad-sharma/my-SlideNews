package com.example.slidenews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class emptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        String url=getIntent().getStringExtra("name");
        Uri uri=Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        Context c=getApplicationContext();
        c.startActivity(intent);

    }
}