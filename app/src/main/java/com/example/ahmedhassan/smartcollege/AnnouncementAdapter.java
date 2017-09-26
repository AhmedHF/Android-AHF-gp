package com.example.ahmedhassan.smartcollege;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed Nazeer on 5/10/2017.
 */

public class AnnouncementAdapter extends ArrayAdapter {

    public class AnnouncementHolder {
        TextView title_text,date_text;
    }
    private Context context;
    //private ArrayList<String> author;
    private ArrayList<Announcement> announcements;
    public AnnouncementAdapter(Context context, ArrayList<Announcement>announcements){
        super(context,0,announcements);
        this.context=context;
        this.announcements =announcements;
    }

    public AnnouncementAdapter(Context context, int resource) {
        super(context, resource);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        AnnouncementHolder holder=new AnnouncementHolder();
        if (convertView == null) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.announcement_list_item, null);
            holder.title_text = (TextView) convertView.findViewById(R.id.announcemenTitleTextView);
            holder.date_text = (TextView) convertView.findViewById(R.id.announcemenDateTextView);
            convertView.setTag(holder);
        }
        else {
            holder = (AnnouncementHolder) convertView.getTag();
        }
        // Lookup view for data population

        // Populate the data into the template view using the data object
        holder.title_text.setText(announcements.get(position).getTitle());
        holder.date_text.setText(announcements.get(position).getDate());
        return convertView;
    }

}
