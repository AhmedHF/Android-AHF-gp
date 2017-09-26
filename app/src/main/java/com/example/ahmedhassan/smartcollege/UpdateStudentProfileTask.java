package com.example.ahmedhassan.smartcollege;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ahmed Nazeer on 5/2/2017.
 */

public class UpdateStudentProfileTask extends AsyncTask<String, String, Void> {

    Student student;//= new Student();
    Context ctx;


    LoginInterface profileInterfase;

    public void setProfileInterfase(LoginInterface profileInterfase) {
        this.profileInterfase = profileInterfase;
    }

    public UpdateStudentProfileTask() {

    }

    @Override
    protected Void doInBackground(String... params) {
        student = new Student();
        URL url = null;
        try {
            String ur = params[0];

            url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // System.out.println("hello");
            Log.e("aa", "con");
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String mail =StudentLogin.session.getStudentMail();
            String phone = StudentLogin.session.getStudentPhone();
            String ID = StudentLogin.session.getID();

            String data = URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8") + "&"
                    + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                    + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8") + "&"
                    + URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("updateProfileInfo", "UTF-8");
            // httpURLConnection.connect();
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            String result = "";
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
            //name,country,gpa,level,major,minor
            /*String name = params[5];
            String country = params[6];
            String gpa = params[7];
            String level = params[8];
            String major = params[9];
            String minor = params[10];

            student.setStudentMajor(major);
            student.setStudentMinor(minor);
            student.setStudentMail(mail);
            student.setStudentPhone(phone);
            student.setStudentCountry(country);
            student.setStudentGPA(gpa);
            student.setState("valid");
            student.setStudentID(ID);
            student.setStudentName(name);
            student.setStudentLevel(level);
*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
/*
    @Override
    protected void onPostExecute(Student s) {
        student = s;
        super.onPostExecute(s);
        if (profileInterfase != null) {

            profileInterfase.getStudentInfo(s);

            /*String ss=s.getStudentName();
            studentData.getStudentData(student);*/

      //  }
    //}
}