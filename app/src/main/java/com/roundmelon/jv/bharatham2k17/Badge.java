package com.roundmelon.jv.bharatham2k17;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;

public class Badge extends AppCompatActivity {

    //CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private Button btnShare;
    private Button logout;
    private Button shareExternal;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);




        btnShare = (Button)findViewById(R.id.btnShare);

        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();


        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText("" + name + " " + surname);
        ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Making your badge... Please Wait...");
        progressDialog.show();

        new DownloadImage((ImageView)findViewById(R.id.profileImage),this,progressDialog).execute(imageUrl);



        shareDialog = new ShareDialog(this);


        FacebookSdk.sdkInitialize(getApplicationContext());


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                ImageView imageView= (ImageView) findViewById(R.id.profileImage);
                Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG,20,stream);

                byte[] bitmapdata = stream.toByteArray();



                Bitmap pic = BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(pic)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });




        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();

                finish();
            }
        });


        shareExternal = (Button)findViewById(R.id.shareExternal);
        shareExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String completePath = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getPath();
                fileName = "/A3K/profile_picture.png";
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri imgUri = Uri.parse(completePath+fileName);
                Log.d("LINK",imgUri.getPath());


                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM,imgUri);
                startActivity(Intent.createChooser(sharingIntent,"Share Image To"));
            }
        });


        Typeface bebas = Typeface.createFromAsset(getAssets(),  "fonts/bebasneue.ttf");

        logout.setTypeface(bebas);
        btnShare.setTypeface(bebas);
        shareExternal.setTypeface(bebas);



    }


    @Override
    public void onBackPressed() {
        /*super.onBackPressed();

        finish();*/
        Snackbar.make(findViewById(R.id.rootView),"Please logout to return", Snackbar.LENGTH_SHORT).show();
       // Toast.makeText(this,"Please logout to return",Toast.LENGTH_SHORT).show();
    }
}
