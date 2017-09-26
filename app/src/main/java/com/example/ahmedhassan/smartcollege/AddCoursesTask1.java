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

/**
 * Created by Ahmed Hassan on 15/06/2017.
 */

public class AddCoursesTask1 extends AsyncTask<String, String, String> {
    AddCourseInterface1 addCourseInterface1;
    String result = "";
//    Course course;
//    ArrayList<Course> courseArrayList;

    public void setAddCourseInterface(AddCourseInterface1 addCourseInterface1) {
        this.addCourseInterface1 = addCourseInterface1;
    }

    private String getResultFromJSON(String strjson)
            throws JSONException {
        JSONObject stateObject = new JSONObject(result);
        String res = stateObject.getString("addCourse");
        return res;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
//        course = new Course();
        try {
            URL url = new URL(params[1]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8")
                    + "&" + URLEncoder.encode("courseId", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8")
                    + "&" + URLEncoder.encode("studentId", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            Log.e("aaa",params[3]);

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

    @Override
    protected void onPostExecute(String result) {
        if (addCourseInterface1 != null) {
            addCourseInterface1.getResponse(result);
        }
    }
}
