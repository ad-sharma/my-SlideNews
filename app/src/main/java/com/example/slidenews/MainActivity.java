package com.example.slidenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public List<News> input;
    public String query="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.d("json_str_main_this_one",query);
        mAdapter = new NewsAdapter(getApplicationContext(), query);
        recyclerView.setAdapter(mAdapter);
        Button goButton=(Button)findViewById(R.id.the_go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("json_str","BUTTON CLICKED");
                EditText queryText=findViewById(R.id.edit_query);
                query=queryText.getText().toString();
                Log.d("json_str_main",query);
                Intent newIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(newIntent);
            }
        });

    }
    public void reLoadNews()
    {
        LinearLayout parent=(LinearLayout)findViewById(R.id.query_linear);

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);

        getApplicationContext().startActivity(intent);
    }
}