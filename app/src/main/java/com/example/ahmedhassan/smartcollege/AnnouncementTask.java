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
 * Created by Ahmed Nazeer on 6/14/2017.
 */

public class AnnouncementTask extends AsyncTask<String, Void, ArrayList<Announcement>> {
    public ArrayList<Announcement> announcementList = new ArrayList<Announcement>();

    public void setListener(OnAnnouncementFinished listener) {
        this.listener = listener;
    }

    OnAnnouncementFinished listener;

    public AnnouncementTask(){

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
            //JSONObject resultEllement = resultsArray.getJSONObject(i);
            //List<Movies> movieList=new ArrayList<Movies>();
            //Announcement announ = new Announcement();
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject resultEllement = resultsArray.getJSONObject(i);
                Announcement announ = new Announcement();
                announ.setTitle(resultEllement.getString("title"));
                announ.setBody(resultEllement.getString("body"));
                //String author1=resultEllement.getString("author");
                announ.setDate(resultEllement.getString("date"));
                //String revi=resultEllement.getString("content");
                announcementList.add(announ);
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

        return announcementList;
    }

    @Override
    protected void onPostExecute(ArrayList<Announcement> reviews) {
        announcementList=reviews;

        super.onPostExecute(reviews);
        if(listener != null) {
            int len=announcementList.size();
            listener.announcementFinished(announcementList);
        }
    }}