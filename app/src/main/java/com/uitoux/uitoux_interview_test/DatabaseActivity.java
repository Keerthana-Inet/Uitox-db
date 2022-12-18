package com.uitoux.uitoux_interview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener, MyListAdapter.CallBack {

    SimpleDatabaseHelper simpleDatabaseHelper;
    RecyclerView recyclerView;
    Button btnGet;
    Button btnAdd;
    Button btnUpdate;
    EditText editText,mark1,mark2,mark3,sno;
    MyListAdapter myListAdapter;
    List<DbModel> list = new ArrayList<DbModel>();
    DbModel updateModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        simpleDatabaseHelper = new SimpleDatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        btnGet = findViewById(R.id.btnGet);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        editText = findViewById(R.id.editText);
        sno = findViewById(R.id.sno);
        mark1 = findViewById(R.id.mark1);
        mark2 = findViewById(R.id.mark2);
        mark3 = findViewById(R.id.mark3);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        setAdapter(false);

        btnGet.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnGet.equals(view)) {
            setAdapter(true);
        } else if (btnAdd.equals(view)) {
            if (!editText.getText().toString().isEmpty()) {
                simpleDatabaseHelper.add(sno.getText().toString(),editText.getText().toString(), 1,mark1.getText().toString(),mark2.getText().toString(),mark3.getText().toString());
                setAdapter(true);
            } else {
                Toast.makeText(this, "Enter value", Toast.LENGTH_SHORT).show();
            }
        } else if (btnUpdate.equals(view)) {
            if (!editText.getText().toString().isEmpty()) {
                simpleDatabaseHelper.update(sno.getText().toString(), Long.parseLong(updateModel.id), editText.getText().toString(), updateModel.priority,mark1.getText().toString(),mark2.getText().toString(),mark3.getText().toString());
                setAdapter(true);
            } else {
                Toast.makeText(this, "Enter updated value", Toast.LENGTH_SHORT).show();
            }
            setAdapter(true);
        }
    }

    private void setAdapter(boolean isGetAll) {
        editText.getText().clear();
        sno.getText().clear();
        mark1.getText().clear();
        mark2.getText().clear();
        mark3.getText().clear();
        if (isGetAll) {
            list = simpleDatabaseHelper.getAll();
            if(list.isEmpty()){
                Toast.makeText(this, "DB is empty", Toast.LENGTH_SHORT).show();
            }
        }
        myListAdapter = new MyListAdapter(list, this);
        recyclerView.setAdapter(myListAdapter);
    }

    @Override
    public void onClick(DbModel dbModel, String type) {
        updateModel = dbModel;
        if (type.equals("update")) {
            editText.setText(dbModel.title);
            mark1.setText(dbModel.mark1);
            mark2.setText(dbModel.mark2);
            mark3.setText(dbModel.mark3);
        } else {
            simpleDatabaseHelper.delete(Long.parseLong(updateModel.id));
            setAdapter(true);
        }
    }
}