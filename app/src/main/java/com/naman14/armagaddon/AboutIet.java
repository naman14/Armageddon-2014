package com.naman14.armagaddon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class AboutIet extends ActionBarActivity {


    @Override

    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setTheme(R.style.androidapptheme);
        setTitle("IET");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);





        setContentView(R.layout.aboutiet);}

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }}
