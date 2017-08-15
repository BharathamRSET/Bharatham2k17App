package com.roundmelon.jv.bharatham2k17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Video extends AppCompatActivity {

    //Firebase mRootRef = new Firebase("https://isl-two.firebaseio.com/");

    ArrayList<String> mVideo = new ArrayList<>();
    //String[] mUrl =new String[50];
    ArrayList<String> mUrl = new ArrayList<>();
    int count;

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);



        Firebase.setAndroidContext(this);
        Firebase mRootRef = new Firebase("https://rset-63188.firebaseio.com/");

        listView1 = (ListView)findViewById(R.id.listView1);

        Firebase videoRef = mRootRef.child("video");


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mVideo);

        //final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUrl);

        listView1.setAdapter(arrayAdapter);


        count = 0;
        videoRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String video = dataSnapshot.getValue(String.class);
                String[] parts = video.split("!!!");
                String title = parts[0];
                String url = parts[1];
                mVideo.add(title);
                mUrl.add(count,url);
                //mUrl[count] = url;
                arrayAdapter.notifyDataSetChanged();
                count++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String video = dataSnapshot.getValue(String.class);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("position", String.valueOf(i));
                Intent videoItemIntent = new Intent(Video.this,Youtube.class);
                // videoItemIntent.putExtra("videourl",mUrl[i]);
                videoItemIntent.putExtra("videourl",mUrl.get(i));
                startActivity(videoItemIntent);
            }
        });


    }
}
