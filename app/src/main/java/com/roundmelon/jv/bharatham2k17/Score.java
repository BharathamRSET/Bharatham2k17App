package com.roundmelon.jv.bharatham2k17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class Score extends AppCompatActivity {


    final String[] items_group = new String[]{"GOne","GTwo","GThree","GFour","GFive"};
    final String[] items_ind = new String[]{"one","two","three","four","five"};
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int m = 0;
    int n = 0;
    int[] final_status = new int[7];
    int[] firstNo = new int[5];
    int[] secondNo = new int[5];
    int[] thirdNo = new int[5];

    //use ____ in event names not spaces
    String[][] GOne = new String[3][3];
    String[][] GTwo = new String[3][3];
    String[][] GThree = new String[3][3];
    String[][] GFour = new String[3][3];
    String[][] GFive = new String[3][3];

    String[][] data = new String[5][3];
    int[] dataFlag = new int[6];

    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");

        editText = (TextView) findViewById(R.id.editText);


//beginning of using for
//            for (i = 0; i <items_group.length-1; i++) {
//                final_ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        Map<String,String> map = dataSnapshot.getValue(Map.class);
//
//                        final_status[i] = Integer.parseInt(map.get(items_group[i]));
//                        //Log.d("Status",s);
//
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });
//
//            }
//
//        for (i = 0; i <items_group.length-1; i++) {
//            Log.d("Status for",Integer.toString(final_status[i]));
//        }
///this  was using for

        //using seperate varibales

        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map = dataSnapshot.getValue(Map.class);
                final_status[0] = Integer.parseInt(map.get("GOne"));
                Log.d("Final 0",Integer.toString(final_status[0]));

                final_status[1] = Integer.parseInt(map.get("GTwo"));
                Log.d("Final 1",Integer.toString(final_status[1]));


                final_status[2] = Integer.parseInt(map.get("GThree"));
                Log.d("Final 2",Integer.toString(final_status[2]));


                final_status[3] = Integer.parseInt(map.get("GFour"));
                Log.d("Final 3",Integer.toString(final_status[3]));


                final_status[4] = Integer.parseInt(map.get("GFive"));
                Log.d("Final 4",Integer.toString(final_status[4]));

                trigger();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    public void trigger(){
        int flagGetData = 0;

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");

        for(j=0;j<items_group.length - 1;j++){
            if(final_status[j] == 1){
                ref.child(items_group[j]).child("prizes").child("First").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        firstNo[j] = (int) dataSnapshot.getChildrenCount();
                        Log.d("Firebase..111....",Integer.toString(firstNo[j]));

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


                ref.child(items_group[j]).child("prizes").child("Second").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        secondNo[j] = (int) dataSnapshot.getChildrenCount();
                        Log.d("Firebase..222....",Integer.toString(secondNo[j]));


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


                ref.child(items_group[j]).child("prizes").child("Third").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        thirdNo[j] = (int) dataSnapshot.getChildrenCount();
                        Log.d("Firebase..333....",Integer.toString(thirdNo[j]));


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



            }
        }


        //get data is called before the above loops have finished
        getData();

    }


    public void getData(){

        Log.d("here1","here1");

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");


        for(k=0;k<items_group.length;k++){
            Log.d("here2","here2");
            if(final_status[k] == 1){
                for(l=1;l<=firstNo[k];l++){
                    Log.d("here3","here3");

                    ref.child(items_group[l]).child("prizes").child("First").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Map<String,String> map = dataSnapshot.getValue(Map.class);
                            //String data = map.get(Integer.toString(l));
                            data[k][0] += map.get(Integer.toString(l));
                            data[k][0] += "--";

                            Log.d("First get","kitti");

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }


                /////


                for(m=1;m<=firstNo[k];m++){

                    ref.child(items_group[l]).child("prizes").child("Second").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Map<String,String> map = dataSnapshot.getValue(Map.class);
                            //String data = map.get(Integer.toString(l));
                            data[k][1] += map.get(Integer.toString(m));
                            data[k][1] += "--";

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }

                ///


                for(n=1;n<=firstNo[k];n++){

                    ref.child(items_group[l]).child("prizes").child("Third").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Map<String,String> map = dataSnapshot.getValue(Map.class);
                            //String data = map.get(Integer.toString(l));
                            data[k][2] += map.get(Integer.toString(n));
                            data[k][2] += "--";

                            Log.d("Third get","kitti");

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }



            }
        }
    }

}

