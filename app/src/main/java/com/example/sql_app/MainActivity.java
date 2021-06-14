package com.example.sql_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName,etPhone,etmail;
    StudentInfo studentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
     etName=findViewById(R.id.etName);
     etPhone=findViewById(R.id.etPhone);
     etmail=findViewById(R.id.etMail);

    }
    public void clear(){
        etName.setText(" ");
        etPhone.setText(" ");
        etmail.setText(" ");
    }
    public void btninsert(View view)
    {
        String name=etName.getText().toString().trim();
        String num=etPhone.getText().toString().trim();
        String mail=etmail.getText().toString().trim();

       StudentInfo studentInfo=new StudentInfo(this);
       studentInfo.open();
       studentInfo.createEntry(name,num,mail);


       studentInfo.close();


    } public void btnview(View view)
    {
        startActivity(new Intent(MainActivity.this,com.example.sql_app.Show_data.class));

    } public void btnupdate(View view)
    {
         studentInfo.open();
        long TotalUpdatedRecord= studentInfo.updateEntry("1","Rao Junaid","03126955313",
                "rao231999@gmai.com");
        Toast.makeText(this, "TotalUpdatedRecord", Toast.LENGTH_SHORT).show();
         studentInfo.close();
    } public void btndelete(View view)
    {      studentInfo.open();
             studentInfo.deleteEntry("1");
            studentInfo.close();
    }


}