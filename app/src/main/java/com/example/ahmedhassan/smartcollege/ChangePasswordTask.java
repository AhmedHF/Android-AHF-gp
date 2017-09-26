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
 * Created by Ahmed Nazeer on 5/10/2017.
 */

public class ChangePasswordTask extends AsyncTask<String, String, Void> {

    Student student;//= new Student();
    Context ctx;


    LoginInterface profileInterfase;

    public void setProfileInterfase(LoginInterface profileInterfase) {
        this.profileInterfase = profileInterfase;
    }

    public ChangePasswordTask() {

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

            String password =StudentLogin.session.getPassword();
            //String phone = StudentLogin.session.getStudentPhone();
            String ID = StudentLogin.session.getID();

            String data = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                    + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8") + "&"
                    + URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("changePassword", "UTF-8");
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
}
