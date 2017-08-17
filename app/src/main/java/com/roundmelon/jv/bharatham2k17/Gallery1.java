package com.roundmelon.jv.bharatham2k17;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gallery1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final private int STORAGE_PERMISSION_CODE = 23;

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private List<Upload> uploads;

    private RecyclerView recyclerView;

    Image image;


    private ArrayList<Image> images;


    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery1);
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


        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();

        images = new ArrayList<>();

        progressDialog.setMessage("Loading Images...");
        progressDialog.show();
        progressDialog.setCancelable(false);


        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/gallery_app");
        mDatabase = FirebaseDatabase.getInstance().getReference("gallery");



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new MyAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new MyAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                Log.d("Gallery",snapshot.getChildrenCount()+"");

                progressDialog.dismiss();
                images.clear();
                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    Log.d("Gallery",upload.getUrl());
                    Image image = new Image();
                    uploads.add(upload);
                    image.setLarge(upload.getUrl());
                    images.add(image);


                }
                //creating adapter
                MyAdapter adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        else if (id == R.id.nav_badge) {
            if(isWriteStorageAllowed()){

                Intent fbIntent = new Intent(this,Fb.class);
                startActivity(fbIntent);


            }

            //If the app has not the permission then asking for the permission
            requestStoragePermission();

        }
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
            //
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
    private boolean isWriteStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            Toast.makeText(this,"This permission is required to store the badge on your device.",Toast.LENGTH_LONG).show();
        }

        ActivityCompat.requestPermissions((Activity)this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_CODE : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent fbIntent = new Intent(this,Fb.class);
                    startActivity(fbIntent);


                } else {
                    Toast.makeText(this,"Please grant the permission to access this feature.",Toast.LENGTH_LONG).show();

                }
                return;
            }


        }
    }
}

