package com.example.antoniroig_2048game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Integer[] squareNumbers = new Integer[16];
    private GridView tileList;
    private Adaptador tileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileList = findViewById(R.id.tileList);

        for (int i = 0; i < squareNumbers.length; i++){

            squareNumbers[i] = i;
        }

        tileAdapter = new Adaptador(this, squareNumbers);
        tileList.setAdapter(tileAdapter);

    }
}