package com.roundmelon.jv.bharatham2k17;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Score extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final private int STORAGE_PERMISSION_CODE = 23;
    private static long back_pressed;


    //final String[] items_group = new String[]{"WOLF_OF_KAKKANAD_STREET","GAME_OF_THRONES","NADAN_PATTU", "RANGOLI","MOVIE_SCENE_DUBBING", "MIME", "DRAMA", "TABLEAU", "THEMATIC_DANCE_GIRLS", "NOSTALGIA_GIRLS", "GROUP_DANCE_BOYS", "SYNCHRONIZATION", "THIRUVATHIRA", "QUIZ", "DEBATE_ENGLISH", "DEBATE_MALAYALAM", "SHORTFILM", "OLD_MALAYALAM_SONG_DUET", "FACE_PAINTING", "FLOWER_ARRANGEMENT", "GROUP_SONG_WESTERN", "GROUP_SONG_EASTERN", "UNPLUGGED", "LIGHT_MUSIC_VOCAL_MALE", "LIGHT_MUSIC_VOCAL_FEMALE", "HINDUSTANI_CLASSICAL_MUSIC", "CARNATIC_CLASSICAL_MUSIC","WESTERN_VOCAL_SOLO_MALE_FEMALE", "INSTRUMENTAL_MUSIC_STRINGS", "INSTRUMENTAL_MUSIC_KEYBOARD", "INSTRUMENT_PERCUSSION", "INSTRUMENT_WIND", "MONOACT", "MIMICRY", "FANCY_DRESS", "BHARATHANATYAM", "MOHINIYATTOM", "FOLK_DANCE", "ADAPT_TUNE", "ON_THE_SPOT_PAINTING", "PHOTOGRAPHY", "AKSHARASLOKAM", "RECITATION_MAL", "RECITATION_ENG", "KADHAPRASANGAM", "MOCK_PRESS", "ELOCUTION_ENG)", "ELOCUTION_MAL", "EXTEMPORE_ENG", "EXTEMPORE_MAL", "MANGLISH", "TURNAROUND", "MAPPILAPATT"};
    final String[] items_group = new String[]{"ADAPT_TUNE","AKSHARASLOKAM","BHARATHANATYAM","DEBATE_ENGLISH","DEBATE_MALAYALAM","DRAMA","ELOCUTION_ENG","ELOCUTION_MAL","EXTEMPORE_ENG","EXTEMPORE_MAL","FACE_PAINTING","FANCY_DRESS","FLOWER_ARRANGEMENT","FOLK_DANCE","GROUP_DANCE_BOYS","GROUP_SONG_EASTERN","GROUP_SONG_WESTERN","HINDUSTANI_CARNATIC_MUSIC","HINDUSTANI_CLASSICAL_MUSIC","INSTRUMENT_PERCUSSION","INSTRUMENT_WIND","INSTRUMENTAL_MUSIC_KEYBOARD","INSTRUMENTAL_MUSIC_STRINGS","KADHAPRASANGAM","LIGHT_MUSIC_VOCAL_FEMALE","LIGHT_MUSIC_VOCAL_MALE","MANGLISH","MAPPILAPATT","MIME","MIMICRY","MOCK_PRESS","MOHINIYATTOM","MONOACT","MOVIE_SCENE_DUBBING","NOSTALGIA_GIRLS","OLD_MALAYALAM_SONG_DUET","ON_THE_SPOT_PAINTING","PHOTOGRAPHY","QUIZ","RANGOLI","RECITATION_ENG","RECITATION_MAL","SHORTFILM","SYNCHRONIZATION","TABLEAU","THEMATIC_DANCE_GIRLS","THIRUVATHIRA","TURNAROUND","UNPLUGGED","WESTERN_VOCAL_SOLO_MALE_FEMALE","NADAN_PATTU","GAME_OF_THRONES","BHARATHAM_NEWSLETTER","WOLF_OF_KAKKANAD_STREET","POETRY_WRITING_MAL","ESSAY_WRITING_ENG","POSTER_DESIGNING","POETRY_WRITING_ENG","SHORT_STORY_MAL","CARTOON_DRAWING","FILM_REVIEW","ESSAY_WRITING_MAL","PENCIL_DRAWING","SHORT_STORY","PAPER_COLLAGE","GRAFETTI_ART"};
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
    private List<Details> details_array1;
    private List<Details> details_array2;
    private List<Details> details_array3;

    String[][] data = new String[100][3];

    int[] dataFlag = new int[6];

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Typeface bebas;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);

        Log.d("started",".......");

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/events");
        final Firebase final_ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/final");




        bebas = Typeface.createFromAsset(getResources().getAssets(),  "fonts/bebasneue.ttf");



                pDialog.setMessage("Updating Scores...");
                pDialog.setCancelable(false);
                pDialog.show();


        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for here/////////////////////////////////////////////////////////
                Map<String,String> map = dataSnapshot.getValue(Map.class);

//                pDialog.setMessage("Updating Scores...");
//                pDialog.setCancelable(false);
//                pDialog.show();


                Log.d("here","-----");
                Log.d("No. of events",Integer.toString(items_group.length));
                for(i=0;i<=items_group.length-1;i++) {


                    final_status[i] = Integer.parseInt(map.get(items_group[i]));
//                    final_ref.child(items_group[i]).setValue("0");
                    Log.d("Final", Integer.toString(final_status[i]));

                }


                trigger();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();

            if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
            else Snackbar.make(findViewById(R.id.rootView),"Press Once More To Exit.", Snackbar.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent homeIntent = new Intent(this,HomeActivity.class);
            startActivity(homeIntent);
            finish();
        } else if (id == R.id.nav_scores) {
            //Intent scoreIntet = new Intent(this,Score2.class);
            Intent scoreIntet = new Intent(this,Chart1.class);
            startActivity(scoreIntet);
            finish();

        }
        else if (id == R.id.nav_updates) {
            Intent updateIntent = new Intent(this,Score.class);
            startActivity(updateIntent);
            finish();

        }


        else if (id == R.id.nav_videos){
            Intent galleryIntent = new Intent(this,Video1.class);
            startActivity(galleryIntent);
            finish();
        }
        else if (id == R.id.nav_website){

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bharatham2k17.com")));
            //
        }
        else if (id == R.id.nav_schedule){
            Intent galleryIntent = new Intent(this,Updates1.class);
            startActivity(galleryIntent);
            finish();
        }
        else if (id == R.id.nav_gallery){
            Intent galleryIntent = new Intent(this,Gallery1.class);
            startActivity(galleryIntent);
            finish();
            //
        }else if (id == R.id.nav_facebook){
            try {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1855389091393568"));
                startActivity(intent);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bharatham2017")));
            }
        }else if(id == R.id.nav_instagram){
            //change page id
            Uri uri = Uri.parse("http://instagram.com/_u/bharatham2017");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/bharatham2017/?hl=en")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

                        mAdapter = new DetailedAdapter(getApplicationContext(),details_array1,details_array2,details_array3,bebas);

//                        mAdapter = new DetailedAdapter(getApplicationContext(),details_array1,details_array2,details_array3);
                        //adding adapter to recyclerview
                        //recyclerView.setAdapter(adapter);
                        recyclerView.setAdapter(mAdapter);

                        if(pDialog.isShowing()){
                            pDialog.dismiss();
                        }



                    }
                }

//                if(pDialog.isShowing())
//                    pDialog.dismiss();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {



            }
        });



    }
}

