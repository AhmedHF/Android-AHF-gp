package com.example.ahmedhassan.smartcollege;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 13/06/2017.
 */

public class AddCoursesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Course> courseArrayList;

    public class ViewHolder {
        TextView name;
        TextView id;

        public ViewHolder(View convertView) {
            id = (TextView) convertView.findViewById(R.id.courseID);
            name = (TextView) convertView.findViewById(R.id.courseName);
        }
    }

    public AddCoursesAdapter(Context context, ArrayList<Course> arrayList) {
        this.context = context;
        courseArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return courseArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = courseArrayList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_allcourses, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String id = course.getCode();
        String name = course.getName();
        viewHolder.id.setText(id);
        viewHolder.name.setText(name);
        return convertView;
    }
}

