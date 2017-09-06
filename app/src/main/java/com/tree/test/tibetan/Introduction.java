package com.tree.test.tibetan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.google.android.gms.ads.MobileAds;

public class Introduction extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String title1 = getString(R.string.title1);
        String description1 = getString(R.string.des1);
        int img1 = R.drawable.dalai_lama;
        int color1 = Color.rgb(92, 107, 192);

        String title2 = getString(R.string.title2);
        String description2 = getString(R.string.des2);
        int img2 = R.drawable.questions;
        int color2 = Color.rgb(76, 175, 80);

        String title3 = getString(R.string.title3);
        String description3 = getString(R.string.des3);
        int img3 = R.drawable.wish;
        int color3 = Color.rgb(11, 134, 235);


        addSlide(AppIntroFragment.newInstance(title1, description1, img1, color1));
        addSlide(AppIntroFragment.newInstance(title2, description2, img2, color2));
        addSlide(AppIntroFragment.newInstance(title3, description3, img3, color3));

        showSkipButton(false);
        setProgressButtonEnabled(true);


        MobileAds.initialize(this, getString(R.string.appId));

    }//onCreateEND

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        Intent i = new Intent(this, Question1.class);
        this.startActivity(i);
        finish();

    }


}//classEND
