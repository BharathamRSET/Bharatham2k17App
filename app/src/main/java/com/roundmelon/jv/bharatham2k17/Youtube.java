package com.roundmelon.jv.bharatham2k17;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private String GOOGLE_API_KEY = "AIzaSyD4i1r8UnyexUNyGs_r-mFNNfndsl-PEG0";


    /*change api key



    dont forget  to



    change



    it


    */








    private String YOUTUBE_VIDEO_ID;
    //private String url;
   // private String YOUTUBE_VIDEO_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Intent intent = getIntent();
        YOUTUBE_VIDEO_ID = intent.getExtras().getString("videourl");


        //Bundle bundle = getIntent().getExtras();
        //YOUTUBE_VIDEO_ID = bundle.getString("videourl");


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
//        Toast.makeText(this, "Initialized Youtube Player Successfully", Toast.LENGTH_LONG).show();
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if(!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }


    }



    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
//            Toast.makeText(Youtube.this, "Video playing", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPaused() {
//            Toast.makeText(Youtube.this, "Video paused", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
//            Toast.makeText(Youtube.this, "Ad", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onVideoStarted() {
//            Toast.makeText(Youtube.this, "Video has started!", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }

    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        Toast.makeText(this, "Failed to Initialize Youtube Player", Toast.LENGTH_SHORT).show();
    }



}

