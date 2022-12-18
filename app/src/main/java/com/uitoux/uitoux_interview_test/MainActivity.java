package com.uitoux.uitoux_interview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraint_header,constr_lyt;
    CardView cardView;
    ScrollView scrollView;
    Button btn_add,database;
    ImageView back_arrow;
    TextView cancel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraint_header= findViewById(R.id.constraint_header1);
        constr_lyt= findViewById(R.id.constr_lyt);
        cardView= findViewById(R.id.cardview);
        scrollView= findViewById(R.id.scrollview);
        btn_add= findViewById(R.id.btn_add);
        database= findViewById(R.id.database);
        back_arrow= findViewById(R.id.img_back);
        cancel= findViewById(R.id.cancel_button);
        cancel.setOnClickListener(view -> {
            constraint_header.setBackgroundResource(R.drawable.bg_gradient);
            cardView.setVisibility(View.VISIBLE);
            database.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        });
        back_arrow.setOnClickListener(view -> {
            constraint_header.setBackgroundResource(R.drawable.bg_gradient);
            cardView.setVisibility(View.VISIBLE);
            database.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        });
        constr_lyt.setOnClickListener(view -> {
            constraint_header.setBackgroundResource(R.color.white);
            scrollView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
            database.setVisibility(View.GONE);
        });
        btn_add.setOnClickListener(view -> {
            constraint_header.setBackgroundResource(R.color.white);
            scrollView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
            database.setVisibility(View.GONE);
        });
        database.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(),DatabaseActivity.class);
            startActivity(intent);
        });
    }
}