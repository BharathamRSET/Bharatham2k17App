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
    static int[] firstNo = new int[5];
    int[] secondNo = new int[5];
    int[] thirdNo = new int[5];

    int  firstData,secondData,thirdData;

    //use ____ in event names not spaces
//    String[][] GOne = new String[3][3];
//    String[][] GTwo = new String[3][3];
//    String[][] GThree = new String[3][3];
//    String[][] GFour = new String[3][3];
//    String[][] GFive = new String[3][3];

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





        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("here","here////");
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
//        int  firstData,secondData,thirdData;

        Log.d("Trigger","trigger started");

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");




        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(j=0;j<items_group.length-1;j++) {
                    if (final_status[j] == 1) {

                        firstNo[j] = (int) dataSnapshot.child(items_group[j]).child("prizes").child("First").getChildrenCount();

                        Log.d("HelloFirst", "" + dataSnapshot.child(items_group[j]).child("prizes").child("First").getChildrenCount());

                        if (firstNo[j] != 0) {

                            for(l=1;l<=firstNo[j];l++) {


                                String data1 = String.valueOf(dataSnapshot.child(items_group[j]).child("prizes").child("First").child("" + l).getValue());
                                data[j][0] += data1;
                                data[j][0] += "--";

                                Log.d("First get", "kitti" + data[j][0]);

                            }
                        }


                        secondNo[j] = (int) dataSnapshot.child(items_group[j]).child("prizes").child("Second").getChildrenCount();


                        Log.d("HelloSecond", "" + dataSnapshot.child(items_group[j]).child("prizes").child("Second").getChildrenCount());

                        if (secondNo[j] != 0) {

                            for(l=1;l<=secondNo[j];l++) {


                                String data1 = String.valueOf(dataSnapshot.child(items_group[j]).child("prizes").child("Second").child("" + l).getValue());
                                data[j][1] += data1;
                                data[j][1] += "--";

                                Log.d("Second get", "kitti" + data[j][1]);

                            }
                        }
                        thirdNo[j] = (int)dataSnapshot.child(items_group[j]).child("prizes").child("Third").getChildrenCount();

                        Log.d("HelloThird", "" + dataSnapshot.child(items_group[j]).child("prizes").child("Third").getChildrenCount());

                        if (thirdNo[j] != 0) {

                            for(l=1;l<=thirdNo[j];l++) {


                                String data1 = String.valueOf(dataSnapshot.child(items_group[j]).child("prizes").child("Third").child("" + l).getValue());
                                data[j][2] += data1;
                                data[j][2] += "--";

                                Log.d("Third get", "kitti" + data[j][2]);

                            }
                        }


                        Log.d("First Children", "" + j + "--" + Integer.toString(firstNo[j]));
                        Log.d("Second Children", Integer.toString(secondNo[j]));
                        Log.d("Third Children", Integer.toString(thirdNo[j]));
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }



}

