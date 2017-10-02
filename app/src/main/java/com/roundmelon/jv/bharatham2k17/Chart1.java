package com.roundmelon.jv.bharatham2k17;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Chart1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static long back_pressed;


    final private int STORAGE_PERMISSION_CODE = 23;
    float oneChart;
    float twoChart;
    float threeChart;
    float fourChart;
    float fiveChart;
    String oneCharts;
    String twoCharts;
    String threeCharts;
    String fourCharts;
    String fiveCharts;
    BarChart chart;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart1);
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


        Firebase.setAndroidContext(this);
        pDialog = new ProgressDialog(this);



        //Creating firebase object
        Firebase ref =new Firebase("https://bharatham-2k17.firebaseio.com/Score");

        //Getting values to store
        String aryans = "0";
        String mughals = "0";
        String rajputs = "0";
        String spartans = "0";
        String vikings = "0";

        //Creating Person object
        House house = new House();

        //Adding values
        house.setAryans(aryans);
        house.setMughals(mughals);
        house.setRajputs(rajputs);
        house.setSpartans(spartans);
        house.setVikings(vikings);


        //Storing values to firebase
//        ref.child("House").setValue(house);
//          ref.child("House");

        pDialog.setMessage("Updating Scores...");
        pDialog.setCancelable(false);
        pDialog.show();







        //Value event listener for realtime data update
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("Snapshot",snapshot.getChildrenCount()+"");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    House house = postSnapshot.getValue(House.class);

                    //Adding it to a string
//                    String string = "Name: "+person.getName()+"\nAddress: "+person.getAddress()+"\n\n";
//                    Log.d("Sna",house.getAll());
                    oneCharts = house.getAryans();
                    twoCharts = house.getMughals();
                    threeCharts = house.getRajputs();
                    fourCharts = house.getSpartans();
                    fiveCharts = house.getVikings();


                    oneChart = Float.parseFloat(oneCharts);
                    twoChart = Float.parseFloat(twoCharts);
                    threeChart = Float.parseFloat(threeCharts);
                    fourChart = Float.parseFloat(fourCharts);
                    fiveChart = Float.parseFloat(fiveCharts);

                    if(pDialog.isShowing())
                        pDialog.dismiss();
                    BarData data = new BarData(getXAxisValues(), getDataSet());
                    chart.setData(data);
                    chart.setDescription("");
                    chart.animateY(2000);
                    chart.invalidate();

                    //Displaying it on textview
//                    textViewPersons.setText(string);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());


            }
        });


        chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateY(2000);
        chart.invalidate();



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
            back_pressed = System.currentTimeMillis();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
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
//
//        else if (id == R.id.nav_selfie) {
//            Intent selfieIntent = new Intent(this,Selfie.class);
//            startActivity(selfieIntent);
//
//        } else if (id == R.id.nav_selfiegallery) {
//            Intent  selfieGalleryintent = new Intent(this,SelfieGallery.class);
//            startActivity(selfieGalleryintent);
//        }
        else if (id == R.id.nav_videos){
            Intent galleryIntent = new Intent(this,Video1.class);
            startActivity(galleryIntent);
            finish();
        }
        else if (id == R.id.nav_website){
//            Intent galleryIntent = new Intent(this,Web.class);
//            startActivity(galleryIntent);
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






    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(oneChart, 0);
        valueSet1.add(v1e1);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(twoChart, 0);
        valueSet2.add(v2e1);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(threeChart, 0);
        valueSet3.add(v3e1);

        ArrayList<BarEntry> valueSet4 = new ArrayList<>();
        BarEntry v4e1 = new BarEntry(fourChart, 0);
        valueSet4.add(v4e1);

        ArrayList<BarEntry> valueSet5 = new ArrayList<>();
        BarEntry v5e1 = new BarEntry(fiveChart, 0);
        valueSet5.add(v5e1);





        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Aryans");
        barDataSet1.setColor(Color.rgb(131,76,183));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Mughals");
        barDataSet2.setColor(Color.rgb(233,84,3));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Rajputs");
        barDataSet3.setColor(Color.rgb(255,235,59));
        BarDataSet barDataSet4 = new BarDataSet(valueSet4, "Spartans");
        barDataSet4.setColor(Color.rgb(183,28,28));
        BarDataSet barDataSet5 = new BarDataSet(valueSet5, "Vikings");
        barDataSet5.setColor(Color.rgb(13,71,161));




        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);
        dataSets.add(barDataSet5);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("");

        return xAxis;
    }

}
