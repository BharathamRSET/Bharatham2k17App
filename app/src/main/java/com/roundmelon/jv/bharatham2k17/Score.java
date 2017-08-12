package com.roundmelon.jv.bharatham2k17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class Score extends AppCompatActivity {

    final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
}
