package com.naman14.armagaddon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class AboutIetDtu extends ActionBarActivity {


    @Override

    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);

        setTheme(R.style.gdtheme);
        setTitle("IET DTU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);





        setContentView(R.layout.aboutietdtu);}


    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }}