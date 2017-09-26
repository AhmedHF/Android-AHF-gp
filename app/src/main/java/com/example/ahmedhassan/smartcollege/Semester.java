package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Semester extends AppCompatActivity {
    ListView listView;
    // Array of strings...
    String[] courses = {"Course1", "Course2", "Course3", "Course4",
            "Course5", "Course6", "Course7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
        listView = (ListView) findViewById(R.id.listviewAllCourses);

        ArrayAdapter adapter = new ArrayAdapter
                (this, R.layout.list_item_allcourses, R.id.courseName, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Semester.this, CourseDetails.class);
                startActivity(intent);
            }
        });
    }
}
