package com.example.ahmedhassan.smartcollege;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Ahmed Nazeer on 5/10/2017.
 */

public class FetchAnnouncment extends AsyncTask<String, String, ArrayList<Announcement>> {
    public ArrayList<Announcement> announcementList = new ArrayList<Announcement>();
    OnAnnouncementFinished listener;

    public FetchAnnouncment(OnAnnouncementFinished listener){
        this.listener=listener;
    }

    @Override
    protected ArrayList<Announcement> doInBackground(String... urls) {
        URL url=null ;

        String line = "";
        String data = "";////////////////////////////////////////////////////

        try {
            StringBuffer sb = new StringBuffer();

            url =new URL(urls[0]);
            URLConnection tc = url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(tc.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                data = sb.toString();
            }

            JSONObject dataObject = new JSONObject(data);
            JSONArray resultsArray = dataObject.getJSONArray("results");
            //List<Movies> movieList=new ArrayList<Movies>();
            for (int i = 0; i < resultsArray.length(); i++) {
                Announcement announcement = new Announcement();

                JSONObject resultEllement = resultsArray.getJSONObject(i);
                announcement.setTitle(resultEllement.getString("author"));
                String author1=resultEllement.getString("author");
                announcement.setDate(resultEllement.getString("content"));
                //announcement.setBody(resultEllement.getString("content"));

                String revi=resultEllement.getString("content");
                announcementList.add(announcement);
            }
            Log.v("ddddddddddddddd", data);

            String result = resultsArray.toString();
            return announcementList;


        } catch (UnknownHostException uh) {
            Log.v("NewWebHelper", "Unknown host :");
            uh.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.v("NewWebHelper", "FileNotFoundException :");
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("NewWebHelper", "IOException :");
            e.printStackTrace();
        } catch (Exception e) {
            Log.v("NewWebHelper", "Exception :");
            e.printStackTrace();
        }
        //return responseData;

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Announcement> announcements) {
        super.onPostExecute(announcements);
        if(listener != null) {
            listener.announcementFinished(announcements);
        }
    }
}
