package com.tree.test.tibetan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Result extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    SharedPreferences sp;
    AdView adView;
    InterstitialAd mInterAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        adView = (AdView) findViewById(R.id.avViewr);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Result");
        setSupportActionBar(toolbar);

        displayAnswer1();
        displayAnswer2();
        displayAnswer3();

        initAds();

        mInterAd = new InterstitialAd(this);
        mInterAd.setAdUnitId(getResources().getString(R.string.finish_inter_ad_id));
        mInterAd.loadAd(new AdRequest.Builder().build());
        mInterAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });

    }//onCreateEND

    private void initAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private void displayAnswer1() {
        String ans = getString(R.string.ans1Init);
        ans += "1. " + getPriority(sp.getString("q11", "")) + "\n";
        ans += "2. " + getPriority(sp.getString("q12", "")) + "\n";
        ans += "3. " + getPriority(sp.getString("q13", "")) + "\n";
        ans += "4. " + getPriority(sp.getString("q14", "")) + "\n";
        ans += "5. " + getPriority(sp.getString("q15", "")) + "\n";
        tv1.setText(ans);
    }

    private String getPriority(String nm) {
        if (nm.equalsIgnoreCase(getString(R.string.cow))) {
            return getString(R.string.career);
        } else if (nm.equalsIgnoreCase(getString(R.string.tiger))) {
            return getString(R.string.pride);
        } else if (nm.equalsIgnoreCase(getString(R.string.sheep))) {
            return getString(R.string.love);
        } else if (nm.equalsIgnoreCase(getString(R.string.horse))) {
            return getString(R.string.family);
        } else if (nm.equalsIgnoreCase(getString(R.string.pig))) {
            return getString(R.string.monet);
        } else {
            return "";
        }
    }


    private void displayAnswer2() {
        String ans = "";
        //ans+= "\u25CF Your own personality is "+sp.getString("q21","").toLowerCase()+".\n";
        ans += getString(R.string.ans21, sp.getString("q21", "").toLowerCase());
        ans += getString(R.string.ans22, sp.getString("q22", "").toLowerCase());
        ans += getString(R.string.ans23, sp.getString("q23", "").toLowerCase());
        ans += getString(R.string.ans24, sp.getString("q24", "").toLowerCase());
        ans += getString(R.string.ans25, sp.getString("q25", "").toLowerCase());

//        ans+= "\u25CF Personality of your partner is "+sp.getString("q22","").toLowerCase()+".\n";
//        ans+= "\u25CF Personality of your enemies is "+sp.getString("q23","").toLowerCase()+".\n";
//        ans+= "\u25CF You interpret sex as"+sp.getString("q24","").toLowerCase()+".\n";
//        ans+= "\u25CF Your own life is "+sp.getString("q25","").toLowerCase()+".\n";
        tv2.setText(ans);
    }


    private void displayAnswer3() {

        String ans = "";
        ans += getString(R.string.ans31, modifyRelation(sp.getString("q31", "")));
        ans += getString(R.string.ans32, modifyRelation(sp.getString("q32", "")));
        ans += getString(R.string.ans33, modifyRelation(sp.getString("q33", "")));
        ans += getString(R.string.ans34, modifyRelation(sp.getString("q34", "")));
        ans += getString(R.string.ans35, modifyRelation(sp.getString("q35", "")));


//        ans += "\u25CF Your will never forget " + modifyRelation(sp.getString("q31", "")) + ".\n";
//        ans += "\u25CF You consider " + modifyRelation(sp.getString("q32", "")) + " your best friend" + ".\n";
//        ans += "\u25CF You really love " + modifyRelation(sp.getString("q33", "")) + ".\n";
//        ans += "\u25CF " + modifyRelation(sp.getString("q34", "")) + " is your twin soul.\n";
//        ans += "\u25CF You will remember " + modifyRelation(sp.getString("q35", "")) + " for rest of your life.\n";
        tv3.setText(ans);
    }

    private String modifyRelation(String nm) {
        return nm.replace(getString(R.string.my), getString(R.string.your));
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void finishIt(View v) {
        if (mInterAd.isLoaded()) {
            mInterAd.show();
        } else {
        }
    }


    public void shareIt(View view) {
        String link = "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
        String shareBody = getString(R.string.shareText) + "\n" + link;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share"));
    }

}//classEND
