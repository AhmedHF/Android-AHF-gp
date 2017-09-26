package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatiralsFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    public MatiralsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matirals, container, false);
// get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        Intent intent = getActivity().getIntent();
        Course course = intent.getParcelableExtra("info");
        String courseid = course.getID();
        String department = StudentLogin.session.getStudentMajor();


        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        return view;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Book");
        listDataHeader.add("Lecture");
        listDataHeader.add("Section");
        listDataHeader.add("Lab");
        listDataHeader.add("Project");
        listDataHeader.add("Assignment");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Book 1");
        top250.add("Book 2");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Lecture 1");
        nowShowing.add("Lecture 2");
        nowShowing.add("Lecture 3");
        nowShowing.add("Lecture 4");
        nowShowing.add("Lecture 5");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Section 1");
        comingSoon.add("Section 2");
        comingSoon.add("Section 3");
        comingSoon.add("Section 4");


        List<String> lab = new ArrayList<String>();
        lab.add("Lab 1");
        lab.add("Lab 2");
        lab.add("Lab 3");
        lab.add("Lab 4");
        lab.add("Lab 5");
        lab.add("Lab 6");
        lab.add("Lab 7");
        lab.add("Lab 8");

        List<String> project = new ArrayList<String>();
        project.add("Project 1");
        project.add("Project 2");

        List<String> assig = new ArrayList<String>();
        assig.add("Assigement 1");
        assig.add("Assigement 2");
        assig.add("Assigement 3");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), lab);
        listDataChild.put(listDataHeader.get(4), project);
        listDataChild.put(listDataHeader.get(5), assig);



    }
}


