package com.roundmelon.jv.bharatham2k17;

/**
 * Created by Joseph on 15/08/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Score2 extends AppCompatActivity {


    final String[] items_group = new String[]{"WOLF_OF_KAKKANAD_STREET","GAME_OF_THRONES","NADAN_PATTU", "RANGOLI","MOVIE_SCENE_DUBBING", "MIME", "DRAMA", "TABLEAU", "THEMATIC_DANCE_GIRLS", "NOSTALGIA_GIRLS", "GROUP_DANCE_BOYS", "SYNCHRONIZATION", "THIRUVATHIRA", "QUIZ", "DEBATE_ENGLISH", "DEBATE_MALAYALAM", "SHORTFILM", "OLD_MALAYALAM_SONG_DUET", "FACE_PAINTING", "FLOWER_ARRANGEMENT", "GROUP_SONG_WESTERN", "GROUP_SONG_EASTERN", "UNPLUGGED", "LIGHT_MUSIC_VOCAL_MALE", "LIGHT_MUSIC_VOCAL_FEMALE", "HINDUSTANI_CLASSICAL_MUSIC", "CARNATIC_CLASSICAL_MUSIC","WESTERN_VOCAL_SOLO_MALE_FEMALE", "INSTRUMENTAL_MUSIC_STRINGS", "INSTRUMENTAL_MUSIC_KEYBOARD", "INSTRUMENT_PERCUSSION", "INSTRUMENT_WIND", "MONOACT", "MIMICRY", "FANCY_DRESS", "BHARATHANATYAM", "MOHINIYATTOM", "FOLK_DANCE", "ADAPT_TUNE", "ON_THE_SPOT_PAINTING", "PHOTOGRAPHY", "AKSHARASLOKAM", "RECITATION_MAL", "RECITATION_ENG", "KADHAPRASANGAM", "MOCK_PRESS", "ELOCUTION_ENG)", "ELOCUTION_MAL", "EXTEMPORE_ENG", "EXTEMPORE_MAL", "MANGLISH", "TURNAROUND", "MAPPILAPATT"};

    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int m = 0;
    int n = 0;
    int[] final_status = new int[100];
    static int[] firstNo = new int[100];
    int[] secondNo = new int[100];
    int[] thirdNo = new int[100];

    private DatabaseReference mDatabase;

    //private List<Details> details;
    private ArrayList<String> name1;
    private ArrayList<String> eventName1;
    private ArrayList<String> className1;
    private ArrayList<String> house1;
    private ArrayList<String> position1;
    private ArrayList<String> score1;

    private List<Details> details_array1;


    private ArrayList<String> name2;
    private ArrayList<String> eventName2;
    private ArrayList<String> className2;
    private ArrayList<String> house2;
    private ArrayList<String> position2;
    private ArrayList<String> score2;
    private List<Details> details_array2;

    private ArrayList<String> name3;
    private ArrayList<String> eventName3;
    private ArrayList<String> className3;
    private ArrayList<String> house3;
    private ArrayList<String> position3;
    private ArrayList<String> score3;
    private List<Details> details_array3;



    int firstData, secondData, thirdData;


    //use ____ in event names not spaces
//    String[][] GOne = new String[3][3];
//    String[][] GTwo = new String[3][3];
//    String[][] GThree = new String[3][3];
//    String[][] GFour = new String[3][3];
//    String[][] GFive = new String[3][3];

    String[][] data = new String[100][3];

    int[] dataFlag = new int[6];

    TextView editText;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Log.d("started",".......");

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");






        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for here/////////////////////////////////////////////////////////
                Map<String,String> map = dataSnapshot.getValue(Map.class);


                Log.d("here","-----");
                Log.d("No. of events",Integer.toString(items_group.length));
                for(i=0;i<=items_group.length-1;i++) {


                    final_status[i] = Integer.parseInt(map.get(items_group[i]));
//                    final_ref.child(items_group[i]).setValue("0");
                    Log.d("Final", Integer.toString(final_status[i]));

                }

//                final_status[1] = Integer.parseInt(map.get("GTwo"));
//                Log.d("Final 1",Integer.toString(final_status[1]));
//
//
//                final_status[2] = Integer.parseInt(map.get("GThree"));
//                Log.d("Final 2",Integer.toString(final_status[2]));
//
//
//                final_status[3] = Integer.parseInt(map.get("GFour"));
//                Log.d("Final 3",Integer.toString(final_status[3]));
//
//
//                final_status[4] = Integer.parseInt(map.get("GFive"));
//                Log.d("Final 4",Integer.toString(final_status[4]));

                trigger();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    public void trigger(){



        Log.d("trigger",".........");

        details_array1 = new ArrayList<Details>();
        details_array2 = new ArrayList<Details>();
        details_array3 = new ArrayList<Details>();

        mDatabase = FirebaseDatabase.getInstance().getReference("events");
//        Firebase.setAndroidContext(this);


        Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("trigger data change",".........");

                for(j=0;j<=items_group.length-1;j++) {
                    if (final_status[j] == 1) {

                        Log.d("trigger for",".........");
                        //dismissing the progress dialog
                        Log.d("Events", dataSnapshot.child(items_group[j]).getChildrenCount() + "");
                        //progressDialog.dismiss();
//                        name1.clear();
//                        eventName1.clear();
//                        className1.clear();
//                        house1.clear();
//                        position1.clear();
//                        house1.clear();
//                        score1.clear();
//
//                        name2.clear();
//                        eventName2.clear();
//                        className2.clear();
//                        house2.clear();
//                        position2.clear();
//                        house2.clear();
//                        score2.clear();
//
//                        name3.clear();
//                        eventName3.clear();
//                        className3.clear();
//                        house3.clear();
//                        position3.clear();
//                        house3.clear();
//                        score3.clear();


                        //iterating through all the values in database
                        for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("first").getChildren()) {
                            ///gettinng no of first no
                            Log.d("first data change",".........");
                            firstNo[j] =(int) postSnapshot.getChildrenCount();
                            Details details1 = postSnapshot.getValue(Details.class);
                            //Log.d("",details.getName());
                            //Image image = new Image();
                            details_array1.add(details1);
//                            name1.add(details1.getName());
                            Log.d("name",details1.getName());
//                            eventName1.add(details1.getEventName());
//                            className1.add(details1.getClassName());
//                            house1.add(details1.getHouse());
//                            position1.add(details1.getPosition());
//                            score1.add(details1.getScore());

                            ///image.setLarge(details.getUrl());
                            //images.add(image);


                        }

                        for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("second").getChildren()) {
                            Details details2 = postSnapshot.getValue(Details.class);
                            secondNo[j] = (int) postSnapshot.getChildrenCount();
                            //Log.d("",details.getName());
                            //Image image = new Image();
                            details_array2.add(details2);
//                            name2.add(details2.getName());
                            Log.d("name",details2.getName());
//                            eventName2.add(details2.getEventName());
//                            className2.add(details2.getClassName());
//                            house2.add(details2.getHouse());
//                            position2.add(details2.getPosition());
//                            score2.add(details2.getScore());
                            ///image.setLarge(details.getUrl());
                            //images.add(image);


                        }

                        for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("third").getChildren()) {
                            Details details3 = postSnapshot.getValue(Details.class);
                            thirdNo[j] = (int) postSnapshot.getChildrenCount();
                            //Log.d("",details.getName());
                            //Image image = new Image();
                            details_array3.add(details3);
//                            name3.add(details3.getName());
                            Log.d("name",details3.getName());
//                            eventName3.add(details3.getEventName());
//                            className3.add(details3.getClassName());
//                            house3.add(details3.getHouse());
//                            position3.add(details3.getPosition());
//                            score3.add(details3.getScore());
                            ///image.setLarge(details.getUrl());
                            //images.add(image);


                        }





                        //creating adapter
                        //MyAdapter adapter = new MyAdapter(getApplicationContext(), details);

                        mAdapter = new DetailedAdapter(getApplicationContext(),details_array1,details_array2,details_array3);

                        //adding adapter to recyclerview
                        //recyclerView.setAdapter(adapter);
                        recyclerView.setAdapter(mAdapter);

                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {



            }
        });



    }

}