package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class UpadateProfileActivity extends AppCompatActivity implements LoginInterface {
    String data, ID, name, country, level, major, minor, gpa;
    TextView nameTextView, idTextView, gpaTextView, levelTextView, countryTextView, majorTextView, minorTextView;
    EditText mailEditText, phoneEditText;
    Student student;
    String reg_url = StudentLogin.session.getUrl() + "ProfileManngement";//192.168.1.6     192.168.43.177
    LoginInterface listener;

    public void setListener(LoginInterface listener) {
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadate_profile);
        student = new Student();
        //data=getIntent().getStringExtra("newData");

        nameTextView = (TextView) findViewById(R.id.updateNameTextView);
        idTextView = (TextView) findViewById(R.id.updateIdTextView);
        gpaTextView = (TextView) findViewById(R.id.updateGPATextView);
        countryTextView = (TextView) findViewById(R.id.updateCountryTextView);
        levelTextView = (TextView) findViewById(R.id.updateLevelTextView);
        majorTextView = (TextView) findViewById(R.id.updateMajorTextView);
        minorTextView = (TextView) findViewById(R.id.updateMinorTextView);
        mailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        /*data=getIntent().getStringExtra("newData");
        String values[]=data.split("==");
        //String data=name+"=="+id+"="+country+"=="+gpa+"=="+level+"=="+major+"=="+minor+"=="+mail+"=="+mobile;
         name=values[0];
        ID=values[1];
         country=values[2];
         gpa=values[3];
         level=values[4];
         major=values[5];
         minor=values[6];
         String mail=values[7];
        String mobile=values[8];
*/
        nameTextView.setText(StudentLogin.session.getStudentName());//name
        idTextView.setText(StudentLogin.session.getID());//ID
        gpaTextView.setText(StudentLogin.session.getStudentGPA());//gpa
        levelTextView.setText(StudentLogin.session.getStudentLevel());//level
        countryTextView.setText(StudentLogin.session.getStudentCountry());//country
        majorTextView.setText(StudentLogin.session.getStudentMajor());//major
        minorTextView.setText(StudentLogin.session.getStudentMinor());//minor
        mailEditText.setText(StudentLogin.session.getStudentMail());//mail
        phoneEditText.setText(StudentLogin.session.getStudentPhone());//mobile


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upadate_profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_profile) {
            UpdateStudentProfileTask updateStudentProfileTask = new UpdateStudentProfileTask();
            updateStudentProfileTask.setProfileInterfase(this);
            String mail = mailEditText.getText().toString();
            StudentLogin.session.setStudentMail(mail);
            String phone = phoneEditText.getText().toString();
            StudentLogin.session.setStudentPhone(phone);
            updateStudentProfileTask.execute(reg_url, "updateStudentInfo", mailEditText.getText().toString(), phoneEditText.getText().toString(), ID,
                    name, country, gpa, level, major, minor

            );

            /*Intent intent=new Intent(UpadateProfileActivity.this,Profile.class);
            startActivity(intent);*/

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getStudentInfo(Student s) {
        student = s;

    }
}
