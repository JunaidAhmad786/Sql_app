package com.example.sql_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Show_data extends AppCompatActivity {
     TextView etresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        StudentInfo studentInfo=new StudentInfo(this);
        studentInfo.open();
         etresult.setText(studentInfo.getData());
        studentInfo.close();
    }
}