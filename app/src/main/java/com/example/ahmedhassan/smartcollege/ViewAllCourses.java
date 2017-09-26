package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAllCourses extends AppCompatActivity implements AddCourseInterface {
    ListView listView;
    ArrayList<Course> courseArrayList;
    String url = StudentLogin.session.getUrl() + "ViewAllcourses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);

        listView = (ListView) findViewById(R.id.listviewAllCourses);
        courseArrayList = new ArrayList<>();
        ViewAllCoursesTask task = new ViewAllCoursesTask();
        task.setAddCourseInterface(this);
        task.execute(url, StudentLogin.session.getStudentDataID());

    }

    @Override
    public void getCourses(ArrayList<Course> course) {
        courseArrayList = course;
        AddCoursesAdapter adapter = new AddCoursesAdapter(this, courseArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course1 = courseArrayList.get(i);
//                String courseid = course1.getID();
//                AddCoursesTask1 task = new AddCoursesTask1();
//                AddCourseInterface1 addCourseInterface1 = null;
//                task.setAddCourseInterface(addCourseInterface1);
//                String ff = StudentLogin.session.getStudentDataID();
//                task.execute("SelectCourse1", url, courseid, StudentLogin.session.getStudentDataID());
                Intent intent = new Intent(ViewAllCourses.this, CourseDetails.class);
                intent.putExtra("info", course1);
                startActivity(intent);
            }
        });
    }
}
