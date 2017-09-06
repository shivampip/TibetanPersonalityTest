package com.tree.test.tibetan;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Question1 extends AppCompatActivity {


    TextView tv[] = new TextView[5];
    Button bt[] = new Button[5];
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question1);

        tv[0] = (TextView) findViewById(R.id.q1);
        tv[1] = (TextView) findViewById(R.id.q2);
        tv[2] = (TextView) findViewById(R.id.q3);
        tv[3] = (TextView) findViewById(R.id.q4);
        tv[4] = (TextView) findViewById(R.id.q5);

        bt[0] = (Button) findViewById(R.id.b1);
        bt[1] = (Button) findViewById(R.id.b2);
        bt[2] = (Button) findViewById(R.id.b3);
        bt[3] = (Button) findViewById(R.id.b4);
        bt[4] = (Button) findViewById(R.id.b5);

        Button resetB= (Button) findViewById(R.id.resetB1);
        Button nextB= (Button) findViewById(R.id.nextB1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(getResources().getString(R.string.question_1));
        setSupportActionBar(toolbar);

        int pColor= Color.parseColor("#00c853");
        resetB.getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
        nextB.getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);

//        int pColor= Color.parseColor("#81d4fa");
//        bt[0].getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
//        bt[1].getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
//        bt[2].getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
//        bt[3].getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
//        bt[4].getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);



    }//onCreateEND


    public void click(View v) {
        tv[count++].setText(((Button) v).getText());
        v.setEnabled(false);
    }


    public void reset(View v) {
        count = 0;
        for (int i = 0; i < 5; i++) {
            bt[i].setEnabled(true);
            tv[i].setText((i + 1) + "");
        }
    }

    public void next(View v) {
        if (count < 5) {
            Toast.makeText(this, R.string.q1Des, Toast.LENGTH_SHORT).show();
            return;
        }
        String q1[] = {tv[0].getText().toString(), tv[1].getText().toString(), tv[2].getText().toString(), tv[3].getText().toString(), tv[4].getText().toString()};
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("q11", q1[0]);
        editor.putString("q12", q1[1]);
        editor.putString("q13", q1[2]);
        editor.putString("q14", q1[3]);
        editor.putString("q15", q1[4]);
        editor.commit();

        Intent i = new Intent(this, Question2.class);
        this.startActivity(i);
        finish();
    }


    @Override
    public void onBackPressed() {
        // here you want to show the user a dialog box
        new AlertDialog.Builder(this)
                .setTitle(R.string.exitTitle)
                .setMessage(R.string.exitDes)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        }).show();

    }

}//classEND
