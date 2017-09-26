package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity implements LoginInterface {
    EditText userIDEditText, passwordEditText;
    Button loginButton;
    public static Session session;
    LoginInterface listener;
    String id = "", password = "";
    String url = "http://192.168.43.118:8080/TestWeb/";
    String reg_url = url + "ProfileManngement"; //hotspot 192.168.43.177    router 192.168.1.4
    //String myCheck;
    Student student;


    public void setListener(LoginInterface listener) {
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        student = new Student();
        session = new Session(getApplicationContext());
        final BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.setProfileInterfase(this);
        userIDEditText = (EditText) findViewById(R.id.userEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.buttonstudentLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = userIDEditText.getText().toString();
                password = passwordEditText.getText().toString();
                backgroundTask.execute(reg_url, "getProfileInfo", id, password);
            }
        });

    }

    @Override
    public void getStudentInfo(Student s) {
        student = s;
        String state = student.getState();
        //sendData();

        if (state.equals("valid")) {


            String name = student.getStudentName();
            //StudentLogin.session.setStudentName(name);

            String id = student.getStudentID();


            String country = student.getStudentCountry();
            //StudentLogin.session.setStudentCountry(country);

            String gpa = student.getStudentGPA();
            //StudentLogin.session.setStudentGPA(gpa);

            String level = student.getStudentLevel();
            //StudentLogin.session.setStudentLevel(level);

            String major = student.getStudentMajor();
            //StudentLogin.session.setStudentMajor(major);

            String minor = student.getStudentMinor();

            String mail = student.getStudentMail();

            String mobile = student.getStudentPhone();

            String studentDataId = student.getStudentDataId();
//            Log.e("fff",studentDataId);
            //================================== Shared Preferences ========================================
            StudentLogin.session.setID(id);
            StudentLogin.session.setUrl(url);
            StudentLogin.session.setStudentDataID(studentDataId);
            StudentLogin.session.setPassword(password);
            StudentLogin.session.setStudentLevel(level);
            StudentLogin.session.setStudentMajor(major);
            StudentLogin.session.setStudentGPA(gpa);
            StudentLogin.session.setStudentCountry(country);
            StudentLogin.session.setStudentMinor(minor);
            StudentLogin.session.setStudentName(name);
            StudentLogin.session.setStudentPhone(mobile);
            StudentLogin.session.setStudentMail(mail);

            //==============================================================================================


            String data = name + "==" + id + "==" + country + "==" + gpa + "==" + level + "==" + major + "==" + minor + "==" + mail + "==" + mobile;
            //data+=student.getStudentID();
            Intent intent = new Intent(StudentLogin.this, MainActivity.class);
            intent.putExtra("data", data);
            startActivity(intent);
            //finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong ID or Password", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(StudentLogin.this, StudentLogin.class);
            startActivity(intent);
        }

    }/*
    public String sendData(){
        String name=student.getStudentName();
        String id = student.getStudentID();
        String country=student.getStudentCountry();
        String gpa=student.getStudentGPA();
        String level=student.getStudentLevel();
        String major=student.getStudentMajor();
        String minor=student.getStudentMinor();
        String mail=student.getStudentMail();
        String mobile=student.getStudentPhone();
        String data=name+"="+id+"="+country+"="+gpa+"="+level+"="+major+"="+minor+"="+mail+"="+mobile;
        return data;
    }*/

}
