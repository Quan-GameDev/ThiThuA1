package com.example.thithua1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvPerson;
    ArrayList<person_class> listPerson;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPerson = findViewById(R.id.rvPerson);
        listPerson = person_class.init();
        rvPerson.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PersonAdapter(this,listPerson);
        rvPerson.setAdapter(adapter);
    }
}