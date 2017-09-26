package com.example.ahmedhassan.smartcollege;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewSemesters extends AppCompatActivity implements ViewSemestersInterface {
    ArrayList<String> name;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_semesters);
        listView = (ListView) findViewById(R.id.semestername);
        name = new ArrayList<>();
        ViewSemestersTask task = new ViewSemestersTask();
        task.setViewSemestersInterface(this);
        task.execute(StudentLogin.session.getUrl(),StudentLogin.session.getStudentDataID());

    }

    @Override
    public void getSemesters(ArrayList<String> semesters) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, semesters);
        listView.setAdapter(adapter);


    }
}
