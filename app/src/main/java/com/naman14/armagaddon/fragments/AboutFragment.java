package com.naman14.armagaddon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SpannableString s = new SpannableString("Events");
        s.setSpan(new TypefaceSpan("sans-serif-thin"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(s);

        View rootView = inflater.inflate(com.naman14.armagaddon.R.layout.aboutfragment, container,
                false);
        return rootView;
    }
}
