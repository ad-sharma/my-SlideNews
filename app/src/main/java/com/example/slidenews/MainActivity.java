package com.example.slidenews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<News> input;
    public static String query = "";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String str="";
//dummy comment
    //another dummy comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.d("json_str_main_this_one", query);
        mAdapter = new NewsAdapter(getApplicationContext(), query);

        recyclerView.setAdapter(mAdapter);
        Button goButton = (Button) findViewById(R.id.the_go_button);
        goButton.setOnClickListener(v -> {
            Log.d("json_str", "BUTTON CLICKED");
            EditText queryText = findViewById(R.id.edit_query);
            query = queryText.getText().toString();
            Log.d("json_str_main",query);
            Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
            //newIntent.putExtra("message_key",query);
            startActivity(newIntent);
        });

    }


}