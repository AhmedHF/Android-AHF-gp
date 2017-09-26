package com.example.ahmedhassan.smartcollege;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 30/06/2017.
 */

public class ViewSemestersTask extends AsyncTask<String, String, String> {
    ViewSemestersInterface viewSemestersInterface;
    ArrayList<String> semesters;

    public void setViewSemestersInterface(ViewSemestersInterface viewSemestersInterface) {
        this.viewSemestersInterface = viewSemestersInterface;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("ViewSemesters", "UTF-8")
                    + "&" + URLEncoder.encode("studentDataId", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            StringBuffer sb = new StringBuffer();
            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                result = sb.toString();
            }
            IS.close();
            br.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("reee", result);
        return result;
    }
    private ArrayList<String> getAllSemesterFromJSON(String strjson)
            throws JSONException {
        semesters = new ArrayList<>();
        JSONObject stateObject = new JSONObject(strjson);
        JSONArray names = stateObject.getJSONArray("semesters");
        for (int i = 0; i < names.length(); i++) {
            JSONObject nameObject = names.getJSONObject(i);
            String name = nameObject.getString("name").toString();
            semesters.add(name);
        }
        return semesters;
    }
    protected void onPostExecute(String result) {
        try {
            if (viewSemestersInterface != null) {
                viewSemestersInterface.getSemesters(getAllSemesterFromJSON(result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
