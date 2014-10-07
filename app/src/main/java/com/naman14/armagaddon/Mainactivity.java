package com.naman14.armagaddon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.naman14.armagaddon.fragments.AboutFragment;
import com.naman14.armagaddon.fragments.EventsFragment;
import com.naman14.armagaddon.mockup.design.tests.adapter.Navdraweradapter;
import com.naman14.armagaddon.mockup.design.tests.ui.Icons;
import com.wisemandesigns.android.widgets.CircularImageView;

import java.util.ArrayList;

public class Mainactivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private static final String TAG = SplashScreen.class.getSimpleName();

    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;

    @SuppressWarnings("unused")
    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private ArrayList<Icons> icons;
    private Navdraweradapter adapter;
    private String[] MDTitles;
    private TypedArray MDIcons;
    private Handler mHandler = new Handler();

    public CircularImageView iv;

    final Context context = this;

    Fragment events = new EventsFragment();
    Fragment about = new AboutFragment();

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Armageddon_Theme);
        useExtras();

        GCMIntentService.registerAtGCM(this);

        getSupportActionBar().setTitle("Events");
        SharedPreferences first = PreferenceManager
                .getDefaultSharedPreferences(this);

        if (!first.getBoolean("firstTime", false)) {

            SharedPreferences.Editor editor = first.edit();

            editor.putBoolean("firstTime", true);
            editor.commit();

        }

        final ActionBar actionBar = getSupportActionBar();


        getSupportActionBar().setIcon(R.drawable.ic_drawer_white);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);



        setContentView(R.layout.drawer_mockup);


        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        MDTitles = getResources().getStringArray(
                R.array.navigation_main_sections);

        MDIcons = getResources().obtainTypedArray(R.array.drawable_ids);

        icons = new ArrayList<Icons>();

        icons.add(new Icons(MDTitles[0], MDIcons.getResourceId(0, -1)));

        icons.add(new Icons(MDTitles[1], MDIcons.getResourceId(1, -2)));



        MDIcons.recycle();
        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
                mDrawerList, false);

        mDrawerList.addHeaderView(header, null, false);

        adapter = new Navdraweradapter(getApplicationContext(), icons);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        mDrawerToggle = new ActionBarDrawerToggle(

                this, mDrawerLayout, android.R.color.transparent, R.string.drawer_open,
                R.string.drawer_close)

        {
            public void onDrawerClosed(View view) {

                getSupportActionBar().setIcon(R.drawable.ic_drawer_white);
                setActionBarTitle();
                supportInvalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {

                getSupportActionBar().setIcon(R.drawable.ic_drawer_white);
                setActionBarTitle();
                supportInvalidateOptionsMenu();

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

            selectItem(0);
        }
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            mDrawerLayout.closeDrawer(mDrawerList);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectItem(position);
                }
            }, 300);
        }

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.drawer_mockup, menu);

        return super.onCreateOptionsMenu(menu);
    }
    private void useExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String message = extras.getString("message");
            String url = extras.getString("url");

            if (url != null && !"".equalsIgnoreCase(url)) {
                Uri uri = Uri.parse(url);
                String text = title + "\n" + message;
                if (text.length() != 1) {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        }
    }
    public void openUrl(View v) {
        Uri uri = Uri.parse("https://gcm4public.appspot.com");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        switch (item.getItemId()) {

            case R.id.HelpAndFeedback:
                Intent email =new
                        Intent(Intent.ACTION_SEND);

                email.putExtra(Intent.EXTRA_EMAIL,new
                        String[]{"namandwivedi14@gmail.com"});
                email.setType("message/rfc822");
                startActivity(email);

            default:

        }
        ;

        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle() {
        SpannableString s = new SpannableString("IET DTU");
        s.setSpan(new TypefaceSpan("sans-serif-light"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        getSupportActionBar().setTitle(s);

    }

    private void selectItem(int position) {

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {

            case 0:
                ft.replace(R.id.content_frame, events);
                break;

            case 1:
                ft.replace(R.id.content_frame, events);
                break;

            case 2:
                ft.replace(R.id.content_frame, about);
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

        }

        ft.commit();

        mDrawerList.setItemChecked(position, true);

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    public void onArmagaddonClicked(final View view) {
        Intent intent = new Intent(this, ArmagaddonActivity.class);
        startActivity(intent);
    }

    public void onRaspberryClicked(final View view) {
        Intent intent = new Intent(this, Raspberry.class);
        startActivity(intent);
    }

    public void onQuizmozClicked(final View view) {
        Intent intent = new Intent(this, Quizmoz.class);
        startActivity(intent);
    }

    public void onAndroidappClicked(final View view) {
        Intent intent = new Intent(this, Androidapp.class);
        startActivity(intent);
    }

    public void onAnimationClicked(final View view) {
        Intent intent = new Intent(this, Animation.class);
        startActivity(intent);
    }
    public void onAerldeaeroClicked(final View view) {
        Intent intent = new Intent(this, Aerldeaero.class);
        startActivity(intent);
    }
    public void onAutocaddClicked(final View view) {
        Intent intent = new Intent(this, Autocadd.class);
        startActivity(intent);
    }
    public void onGroupdiscussionClicked(final View view) {
        Intent intent = new Intent(this, Groupdiscussion.class);
        startActivity(intent);
    }
    public void showDTU(final View view) {
        Intent intent = new Intent(this, AboutDtu.class);
        startActivity(intent);
    }
    public void showIET(final View view) {
        Intent intent = new Intent(this, AboutIet.class);
        startActivity(intent);
    }
    public void showIetDtu(final View view) {
        Intent intent = new Intent(this, AboutIetDtu.class);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static class CategoriesFragment extends Fragment {

        public static final String ARG_CATEGORY = "category";

        public CategoriesFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_categories,
                    container, false);

            int i = getArguments().getInt(ARG_CATEGORY);

            String List = getResources().getStringArray(
                    R.array.navigation_main_sections)[i];

            getActivity().setTitle(List);

            return rootView;

        }
    }
}
