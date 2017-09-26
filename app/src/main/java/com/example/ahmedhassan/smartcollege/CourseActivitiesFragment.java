package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseActivitiesFragment extends android.support.v4.app.Fragment {

    TextView absenceCount,courseGrade;

    public CourseActivitiesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.course_activities, container, false);
        absenceCount=(TextView)view.findViewById(R.id.absenceCountTextView);
        courseGrade=(TextView)view.findViewById(R.id.courseGradeTextView);
        Intent intent = getActivity().getIntent();
        Course course = intent.getParcelableExtra("info");

        absenceCount.setText(""+course.getCount());
        courseGrade.setText(""+course.getGrade());
        return  view;
    }
}
/*
*
* View view =inflater.inflate(R.layout.course_info_fragment, container, false);

        courseNameTextView=(TextView)view.findViewById(R.id.courseNameTextView);//getView()
        courseCodeTextView=(TextView)view.findViewById(R.id.courseCodeTextView);
        courseDescTextView=(TextView)view.findViewById(R.id.courseDescriptionTextView);

        Course course = getArguments().getParcelable("info");
        String name=course.getName().toString();
        courseNameTextView.setText(""+name+"5555");
        name=courseNameTextView.getText().toString();
        String code=course.getCode().toString();
        courseCodeTextView.setText(""+code);
        String desc=course.getDescription().toString();
        courseDescTextView.setText(""+desc);
        return view;
*
* */


