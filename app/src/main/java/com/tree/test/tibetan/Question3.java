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
import android.widget.EditText;
import android.widget.Toast;

public class Question3 extends AppCompatActivity {

    EditText et[]= new EditText[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question3);


        et[0]= (EditText) findViewById(R.id.yellow);
        et[1]= (EditText) findViewById(R.id.orange);
        et[2]= (EditText) findViewById(R.id.red);
        et[3]= (EditText) findViewById(R.id.white);
        et[4]= (EditText) findViewById(R.id.green);
        Button resetB= (Button) findViewById(R.id.resetB1);
        Button nextB= (Button) findViewById(R.id.nextB1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(getResources().getString(R.string.question_3));
        setSupportActionBar(toolbar);

        int pColor= Color.parseColor("#00c853");
        resetB.getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);
        nextB.getBackground().setColorFilter(pColor, PorterDuff.Mode.MULTIPLY);

    }//onCreateEND



    public void reset(View v){
        for (int i=0; i<5; i++){
            et[i].setText("");
        }
    }

    public void next(View v){
        for (int i=0; i<5; i++){
            if(et[i].getText().toString().equals("")){
                Toast.makeText(this, R.string.fillReq2, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String q3[]= {et[0].getText().toString(), et[1].getText().toString(), et[2].getText().toString(), et[3].getText().toString(), et[4].getText().toString()};
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString("q31",q3[0]);
        editor.putString("q32",q3[1]);
        editor.putString("q33",q3[2]);
        editor.putString("q34",q3[3]);
        editor.putString("q35",q3[4]);
        editor.commit();

        Intent i= new Intent(this, Result.class);
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

