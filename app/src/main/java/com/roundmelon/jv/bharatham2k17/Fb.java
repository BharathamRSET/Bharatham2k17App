package com.roundmelon.jv.bharatham2k17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Fb extends AppCompatActivity {



    Spinner drop_down;
    int flag = 0;
    int choice;
    String option = "Select Your Choice";
    TextView selectBadge;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;

    final String[] spinner_items_group = new String[]{"Option","Bharatham 2k17","Aryans","Mughals","Rajputs","Spartans","Vikings"};


    private ProfileTracker profileTracker;

    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            nextActivity(profile,flag);
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

        drop_down = (Spinner)findViewById(R.id.dropdown);
        selectBadge = (TextView)findViewById(R.id.selectBadge);


        final ArrayAdapter<String> adapterGroup = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinner_items_group);
        drop_down.setAdapter(adapterGroup);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile,flag);
            }
        };
        accessTokenTracker.startTracking();
        
        profileTracker.startTracking();




        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                if(profile==null)
                    Log.d("CHECK","profile is null");
                nextActivity(profile,flag);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);



        drop_down.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    option = spinner_items_group[i];
                    choice = i;

                    flag = 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                flag = 0;


            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile,flag);


    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }




    private void nextActivity(Profile profile,int flag){
        if(profile != null){
            Intent main = new Intent(Fb.this, Badge.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(400,400).toString());
            if(option.equalsIgnoreCase("Select Your Choice")){
                Toast.makeText(Fb.this, "Please Select Your Badge!", Toast.LENGTH_SHORT).show();
            }else{
               main.putExtra("choice",choice);
                Log.d("choice..",Integer.toString(choice));
                startActivity(main);
                finish();

            }

        }
    }

/*
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       /* Intent back2 = new Intent(Fb.this,HomeActivity.class);
        startActivity(back2);
        finish();
    }*/
}
