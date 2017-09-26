package com.example.ahmedhassan.smartcollege;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourses extends AppCompatActivity implements AddCourseInterface, AddCourseInterface1 {
    ListView listView;
    ArrayList<Course> courseArrayList;
    String url = StudentLogin.session.getUrl() + "AddCourses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        listView = (ListView) findViewById(R.id.listviewAllCourses);
        courseArrayList = new ArrayList<>();
        AddCoursesTask task = new AddCoursesTask();
        task.setAddCourseInterface(this);
        task.execute("SelectCourse", url);

    }

    @Override
    public void getCourses(ArrayList<Course> course)  {
        courseArrayList = course;
        AddCoursesAdapter adapter = new AddCoursesAdapter(this, courseArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course1 = courseArrayList.get(i);
                String courseid = course1.getID();
                AddCoursesTask1 task = new AddCoursesTask1();
                AddCourseInterface1 addCourseInterface1 = null;
                task.setAddCourseInterface(addCourseInterface1);
//                String ff = StudentLogin.session.getStudentDataID();
                task.execute("SelectCourse1", url, courseid, StudentLogin.session.getStudentDataID());
//                Intent intent = new Intent(AddCourses.this, CourseDetails.class);
//                startActivity(intent);
               Toast.makeText(getApplicationContext(),"Course added successfully", Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override
    public void getResponse(String result) {
        String finalresult = result;
        if (result.equals("vaild")) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Course course1 = courseArrayList.get(i);
                    listView.removeViewAt(i);
                    //Toast.makeText(getApplicationContext(), "Course added successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
