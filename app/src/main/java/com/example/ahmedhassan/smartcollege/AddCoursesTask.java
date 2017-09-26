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
 * Created by Ahmed Hassan on 13/06/2017.
 */

public class AddCoursesTask extends AsyncTask<String, String, String> {
    AddCourseInterface addCourseInterface;
    String result = "";
    Course course;
    ArrayList<Course> courseArrayList;

    public void setAddCourseInterface(AddCourseInterface addCourseInterface) {
        this.addCourseInterface = addCourseInterface;
    }

    private ArrayList<Course> getAllcoursesFromJSON(String strjson)
            throws JSONException {
        courseArrayList = new ArrayList<>();
        JSONObject stateObject = new JSONObject(result);
        JSONArray courses = stateObject.getJSONArray("courses");
        for (int i = 0; i < courses.length(); i++) {
            JSONObject nameObject = courses.getJSONObject(i);

            course = new Course();
            String name = nameObject.getString("name").toString();
            course.setName(name);

            String id = nameObject.getString("id").toString();
            course.setID(id);

            String code = nameObject.getString("code").toString();
            course.setCode(code);

            String description = nameObject.getString("description").toString();
            course.setDescription(description);

            String grade = nameObject.getString("grade").toString();
            course.setGrade(grade);

            courseArrayList.add(course);
        }
        return courseArrayList;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        course = new Course();
        try {
            URL url = new URL(params[1]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("SelectCourse", "UTF-8");
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
        try {
            if (addCourseInterface != null) {
                addCourseInterface.getCourses(getAllcoursesFromJSON(result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
