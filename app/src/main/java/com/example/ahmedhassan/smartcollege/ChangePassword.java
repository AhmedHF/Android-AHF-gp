package com.example.ahmedhassan.smartcollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    EditText oldPasswordEditText,newPasswordEditText,repeatedNewPasswordEditText;
    Button savePassword;
    String oldPass,newPass,reNewPass;
    String reg_url = "http://192.168.1.4:8084/TestWeb/ProfileManngement";// router 192.168.1.4   hotspot  192.168.43.177

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPasswordEditText=(EditText)findViewById(R.id.oldPasswordEditText);
        newPasswordEditText=(EditText)findViewById(R.id.newPasswordEditText);
        repeatedNewPasswordEditText=(EditText)findViewById(R.id.repeatedNewPasswordEditText);
        savePassword=(Button)findViewById(R.id.savePassword);
    }

    public void updatePassword(View view) {
        oldPass=oldPasswordEditText.getText().toString();
        newPass=newPasswordEditText.getText().toString();
        reNewPass=repeatedNewPasswordEditText.getText().toString();
        if(oldPass.equals(StudentLogin.session.getPassword())){
            if(newPass.equals(reNewPass)){

                StudentLogin.session.setPassword(newPass);
                ChangePasswordTask changePasswordTask=new ChangePasswordTask();
                changePasswordTask.execute(reg_url,"");
            }
            else {
                Toast.makeText(this,"Make Sure That 2 Passwords are Identical",Toast.LENGTH_LONG);
            }
        }

    }
}
