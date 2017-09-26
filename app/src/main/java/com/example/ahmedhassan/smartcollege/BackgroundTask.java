package com.example.ahmedhassan.smartcollege;

/**
 * Created by Ahmed Nazeer on 4/28/2017.
 */


import android.content.Context;
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

public class BackgroundTask extends AsyncTask<String, String, Student> {
    Context ctx;


    LoginInterface profileInterfase;

    public void setProfileInterfase(LoginInterface profileInterfase) {
        this.profileInterfase = profileInterfase;
    }

    public BackgroundTask() {

    }

    String result = "";
    Student student;

    protected Student doInBackground(String... params) {
        String method = params[1];

        student = new Student();

        try {

            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // System.out.println("hello");
            Log.e("aa", "con");
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            //if(method.equals("getStudentInfo")) {
            String ID = params[2];
            String password = params[3];
            String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                    + URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("getProfileInfo", "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            //System.out.println(httpURLConnection.getResponseMessage());
            StringBuffer sb = new StringBuffer();
            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));
            //System.out.println(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                result = sb.toString();
            }
            //==========================================Parsing===============================================

            JSONObject stateObject = new JSONObject(result);
            String state = stateObject.getString("state");
            student.setState(state);
            if (state.equals("valid")) {
                JSONArray studentInfoArray = stateObject.getJSONArray("info");
                for (int i = 0; i < studentInfoArray.length(); i++) {
                    JSONObject nameObject = studentInfoArray.getJSONObject(i);
                    String sname = nameObject.getString("name").toString();


                    student.setStudentName(sname);

                    String id = nameObject.getString("id").toString();
                    student.setStudentID(id);

                    String studentDataId = nameObject.getString("studentDataId").toString();
                    student.setStudentDataId(studentDataId);
                    Log.e("fff",studentDataId);
//                    Log.e("ggg",)

                    String level = nameObject.getString("level").toString();
                    student.setStudentLevel(level);

                    String gpa = nameObject.getString("gpa").toString();
                    student.setStudentGPA(gpa);

                    String major = nameObject.getString("major").toString();
                    student.setStudentMajor(major);

                    String minor = nameObject.getString("minor").toString();
                    student.setStudentMinor(minor);

                    String mail = nameObject.getString("mail").toString();
                    student.setStudentMail(mail);

                    String mobile = nameObject.getString("mobile").toString();
                    student.setStudentPhone(mobile);

                    String country = nameObject.getString("country").toString();
                    student.setStudentCountry(country);
                }
                //JSONObject nameObject=studentInfoArray.get
            }
            //========================================================================================
            IS.close();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    protected void onPostExecute(Student s) {
        student = s;
        super.onPostExecute(s);
        if (profileInterfase != null) {

            profileInterfase.getStudentInfo(s);

            /*String ss=s.getStudentName();
            studentData.getStudentData(student);*/

        }
    }

}



