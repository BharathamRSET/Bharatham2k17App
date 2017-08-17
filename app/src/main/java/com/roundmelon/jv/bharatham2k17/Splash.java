package com.roundmelon.jv.bharatham2k17;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView power;
    Typeface bebas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        power = (TextView)findViewById(R.id.power);
        bebas = Typeface.createFromAsset(getResources().getAssets(),  "fonts/bebasneue.ttf");
        power.setTypeface(bebas);



        final Animation an1= AnimationUtils.loadAnimation(getBaseContext(),R.anim.idle);
        final Animation an2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        final RelativeLayout rl=(RelativeLayout) findViewById(R.id.rl);
        rl.startAnimation(an1);
        an1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {



                //rl.startAnimation(an2);
                finish();




                Intent intent = new Intent(Splash.this,HomeActivity.class);
                startActivity(intent);


            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
