package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseInfoFragment extends android.support.v4.app.Fragment {
    //String code,description,name;
    TextView courseNameTextView, courseCodeTextView, courseDescTextView;

    public CourseInfoFragment() {
    }
    /*
    public CourseInfoFragment(String code,String description,String name) {
        this.code=code;
        this.description=description;
        this.name=name;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_info_fragment, container, false);

        courseNameTextView = (TextView) view.findViewById(R.id.courseNameTextView);//getView()
        courseCodeTextView = (TextView) view.findViewById(R.id.courseCodeTextView);
        courseDescTextView = (TextView) view.findViewById(R.id.courseDescriptionTextView);
        Intent intent = getActivity().getIntent();
        Course course = intent.getParcelableExtra("info");


        String name = course.getName().toString();
        courseNameTextView.setText("" + name );
        name = courseNameTextView.getText().toString();
        String code = course.getCode().toString();
        courseCodeTextView.setText("" + code);
        String desc = course.getDescription().toString();
        courseDescTextView.setText("" + desc);
        return view;
    }
}


