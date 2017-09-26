package com.example.ahmedhassan.smartcollege;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ahmed Nazeer on 5/5/2017.
 */

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setID(String id) {
        prefs.edit().putString("id", id).commit();
        //prefsCommit();
    }

    public void setStudentDataID(String id) {
        prefs.edit().putString("studentdataid", id).commit();
        //prefsCommit();
    }

    public void setPassword(String password) {
        prefs.edit().putString("password", password).commit();
        //prefsCommit();
    }

    public void setUrl(String url) {
        prefs.edit().putString("url", url).commit();
        //prefsCommit();
    }

    public String getUrl() {
        String id = prefs.getString("url", "");
        return id;
    }

    public String getStudentDataID() {
        String id = prefs.getString("studentdataid", "");
        return id;
    }

    public String getID() {
        String id = prefs.getString("id", "");
        return id;
    }

    public String getPassword() {
        String password = prefs.getString("password", "");
        return password;
    }

    //=========================================================================================================
    public String getStudentName() {
        String studentName = prefs.getString("name", "");
        return studentName;
    }


    public void setStudentName(String studentName) {
        prefs.edit().putString("name", studentName).commit();
        //this.studentName = studentName;
    }

    public String getStudentGPA() {
        String studentGPA = prefs.getString("gpa", "");
        return studentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        prefs.edit().putString("gpa", studentGPA).commit();
        //this.studentGPA = studentGPA;
    }

    public String getStudentMajor() {
        String studentMajor = prefs.getString("major", "");
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        prefs.edit().putString("major", studentMajor).commit();
        //this.studentMajor = studentMajor;
    }

    public String getStudentMinor() {
        String studentMinor = prefs.getString("minor", "");
        return studentMinor;
    }

    public void setStudentMinor(String studentMinor) {
        prefs.edit().putString("minor", studentMinor).commit();
        //this.studentMinor = studentMinor;
    }

    public String getStudentMail() {
        String studentMail = prefs.getString("mail", "");
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        prefs.edit().putString("mail", studentMail).commit();
        //this.studentMail = studentMail;
    }

    public String getStudentPhone() {
        String studentPhone = prefs.getString("phone", "");
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        prefs.edit().putString("phone", studentPhone).commit();
        //this.studentPhone = studentPhone;
    }

    public String getStudentCountry() {
        String studentCountry = prefs.getString("country", "");
        return studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        prefs.edit().putString("country", studentCountry).commit();
        //this.studentCountry = studentCountry;
    }

    public String getStudentLevel() {
        String studentLevel = prefs.getString("level", "");
        return studentLevel;
    }

    public void setStudentLevel(String studentLevel) {
        prefs.edit().putString("level", studentLevel).commit();
        //this.studentLevel = studentLevel;
    }


}
