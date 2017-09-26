package com.example.ahmedhassan.smartcollege;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Profile extends AppCompatActivity  {

    String reg_url = "http://192.168.43.118:8080/TestWeb/ProfileManngement";//router 192.168.1.4    hotspot 192.168.43.177

    Student student;
    TextView nameTextView,idTextView,gpaTextView,levelTextView,countryTextView,majorTextView
            ,minorTextView,mailTextView,phoneTextView;
    //===========================================================================
    ViewSwitcher phone_switcher;// = (ViewSwitcher) findViewById(R.id.phone_switcher);
                //phone_switcher.showNext(); //or switcher.showPrevious();
    TextView phoneTextView2 ;//= (TextView) email_switcher.findViewById(R.id.phoneTextView);
    EditText phoneEditText;//=(EditText)phone_switcher.findViewById(R.id.phone1EditText);

    ViewSwitcher email_switcher ;//= (ViewSwitcher) findViewById(R.id.email_switcher);
                //email_switcher.showNext(); //or switcher.showPrevious();
    TextView emailTextView2;// = (TextView) email_switcher.findViewById(R.id.emailTextView);
    EditText mailEditText;//=(EditText)email_switcher.findViewById(R.id.email1EditText);

    //===================================================================================
    String data;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        student=new Student();

        nameTextView=(TextView)findViewById(R.id.nameTextView);
        idTextView=(TextView)findViewById(R.id.idTextView);
        gpaTextView=(TextView)findViewById(R.id.gpaTextView);
        countryTextView=(TextView)findViewById(R.id.countryTextView);
        levelTextView=(TextView)findViewById(R.id.levelTextView);
        majorTextView=(TextView)findViewById(R.id.majorTextView);
        minorTextView=(TextView)findViewById(R.id.minorTextView);
        mailTextView=(TextView)findViewById(R.id.emailTextView);
        phoneTextView=(TextView)findViewById(R.id.phoneTextView);

           //=============================================================================================
           email_switcher = (ViewSwitcher) findViewById(R.id.email_switcher);
           //email_switcher.showNext(); //or switcher.showPrevious();
           emailTextView2 = (TextView) email_switcher.findViewById(R.id.emailTextView);
           mailEditText=(EditText)email_switcher.findViewById(R.id.email1EditText);

           phone_switcher = (ViewSwitcher) findViewById(R.id.phone_switcher);
           //phone_switcher.showNext(); //or switcher.showPrevious();
           phoneTextView2 = (TextView) phone_switcher.findViewById(R.id.phoneTextView);
           phoneEditText=(EditText)phone_switcher.findViewById(R.id.phone1EditText);

           //=============================================================================================



        /*data=getIntent().getStringExtra("newdata");
        String values[]=data.split("==");
        //String data=name+"=="+id+"="+country+"=="+gpa+"=="+level+"=="+major+"=="+minor+"=="+mail+"=="+mobile;
        String name=values[0];
        String id=values[1];
        String country=values[2];
        String gpa=values[3];
        String level=values[4];
        String major=values[5];
        String minor=values[6];
        String mail=values[7];
        String mobile=values[8];
         String n=StudentLogin.session.getID();
         String l=StudentLogin.session.getStudentName();
*/
        nameTextView.setText(StudentLogin.session.getStudentName());//name
        idTextView.setText(StudentLogin.session.getID());//id
        gpaTextView.setText(StudentLogin.session.getStudentGPA());//gpa
        levelTextView.setText(StudentLogin.session.getStudentLevel());//level
        countryTextView.setText(StudentLogin.session.getStudentCountry());//country
        majorTextView.setText(StudentLogin.session.getStudentMajor());//major
        minorTextView.setText(StudentLogin.session.getStudentMinor());//minor
        mailTextView.setText(StudentLogin.session.getStudentMail());//mail
        phoneTextView.setText(StudentLogin.session.getStudentPhone());//mobile


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.profile_menu,menu);
        return true;
    }
    int counter=0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.edit_profile){
            /*
            Intent  intent=new Intent(Profile.this,UpadateProfileActivity.class);
            //intent.putExtra("newData",data);
            startActivity(intent);*/

            if(counter==0) {

                //ViewSwitcher email_switcher = (ViewSwitcher) findViewById(R.id.email_switcher);
                email_switcher.showNext(); //or switcher.showPrevious();
                //TextView emailTextView = (TextView) email_switcher.findViewById(R.id.emailTextView);
                //EditText mailEditText=(EditText)email_switcher.findViewById(R.id.email1EditText);
                String m=StudentLogin.session.getStudentMail();
                String p=StudentLogin.session.getStudentPhone();
                mailEditText.setText(m);

                //ViewSwitcher phone_switcher = (ViewSwitcher) findViewById(R.id.phone_switcher);
                phone_switcher.showNext(); //or switcher.showPrevious();
                //TextView phoneTextView = (TextView) email_switcher.findViewById(R.id.phoneTextView);
                //EditText phoneEditText=(EditText)phone_switcher.findViewById(R.id.phone1EditText);
                phoneEditText.setText(p);

                //StudentLogin.session.set

                item.setIcon(R.mipmap.save);
                counter=1;

            }
            else {
                StudentLogin.session.setStudentMail(mailEditText.getText().toString());
                StudentLogin.session.setStudentPhone(phoneEditText.getText().toString());
                //ViewSwitcher email_switcher = (ViewSwitcher) findViewById(R.id.email_switcher);
                 //emailTextView2 = (TextView) email_switcher.findViewById(R.id.emailTextView);
                emailTextView2.setText(StudentLogin.session.getStudentMail());
                email_switcher.showNext(); //or switcher.showPrevious();

                //TextView clickable_text_view = (TextView) phone_switcher.findViewById(R.id.phoneTextView);
                phoneTextView2.setText(StudentLogin.session.getStudentPhone());
                //ViewSwitcher phone_switcher = (ViewSwitcher) findViewById(R.id.phone_switcher);
                phone_switcher.showNext(); //or switcher.showPrevious();
                UpdateStudentProfileTask updateStudentProfileTask=new UpdateStudentProfileTask();
                updateStudentProfileTask.execute(reg_url,"updateStudentInfo",StudentLogin.session.getStudentMail()
                        ,StudentLogin.session.getStudentPhone(),StudentLogin.session.getID());
                item.setIcon(R.drawable.edit);
                counter=0;
            }


            //item.setIcon(R.drawable.save_icon);
            /*Intent intent = new Intent(this, UpadateProfileActivity.class);
            startActivity(intent);*/
            }



        return true;
    }


/*
    @Override
    public void getStudentInfo(Student s) {
        student = s;
        String ss=student.getStudentName();

        nameTextView.setText(""+student.getStudentName());
        idTextView.setText(student.getStudentID());
        gpaTextView.setText(student.getStudentGPA());
        levelTextView.setText(student.getStudentLevel());
        countryTextView.setText(student.getStudentCountry());
        majorTextView.setText(student.getStudentMajor());
        minorTextView.setText(student.getStudentMinor());
        mailTextView.setText(student.getStudentMail());
        phoneTextView.setText(student.getStudentPhone());
    }*/
}
