package com.roundmelon.jv.bharatham2k17;

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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static long back_pressed;


    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    ImageView mImageView;
    TextView textView1,textView2,textView3,textView4,textView5;

    Typeface bebas;

    final private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

//        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
        fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
//        viewFlipper.setInAnimation(fade_in);
//        viewFlipper.setOutAnimation(fade_out);
////sets auto flipping
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(2500);
//        viewFlipper.startFlipping();




        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);


        bebas = Typeface.createFromAsset(getResources().getAssets(),  "fonts/bebasneue.ttf");
        textView1.setTypeface(bebas);
        textView2.setTypeface(bebas);
        textView3.setTypeface(bebas);
        textView4.setTypeface(bebas);
        textView5.setTypeface(bebas);






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
//
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
        // Handle navigation view item clicks here.
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
//        else if (id == R.id.nav_badge) {
//            if(isWriteStorageAllowed()){
//
//                Intent fbIntent = new Intent(this,Fb.class);
//                startActivity(fbIntent);
//
//
//            }
//
//            //If the app has not the permission then asking for the permission
//            requestStoragePermission();
//
//        }
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
//    private boolean isWriteStorageAllowed() {
//        //Getting the permission status
//        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        //If permission is granted returning true
//        if (result == PackageManager.PERMISSION_GRANTED)
//            return true;
//
//        //If permission is not granted returning false
//        return false;
//    }
//    //Requesting permission
//    private void requestStoragePermission(){
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//
//            Toast.makeText(this,"This permission is required to store the badge on your device.",Toast.LENGTH_LONG).show();
//        }
//
//        ActivityCompat.requestPermissions((Activity)this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case STORAGE_PERMISSION_CODE : {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    Intent fbIntent = new Intent(this,Fb.class);
//                    startActivity(fbIntent);
//
//
//                } else {
//                    Toast.makeText(this,"Please grant the permission to access this feature.",Toast.LENGTH_LONG).show();
//
//                }
//                return;
//            }
//
//
//        }
//    }
}
