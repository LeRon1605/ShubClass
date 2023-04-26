package com.androidexam.shubclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

<<<<<<< HEAD
import com.androidexam.shubclassroom.view.auth.AuthActivity;
=======
import com.androidexam.shubclassroom.view.LoginActivity;
import com.androidexam.shubclassroom.view.RegisterActivity;
import com.androidexam.shubclassroom.view.student.HomeStudentActivity;
import com.androidexam.shubclassroom.view.teacher.HomeTeacherActivity;
>>>>>>> feature(android)/home_view_student

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
<<<<<<< HEAD
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
=======
                startActivity(new Intent(MainActivity.this, HomeStudentActivity.class));
>>>>>>> feature(android)/home_view_student
                finish();
            }
        }, 1000);
    }
}