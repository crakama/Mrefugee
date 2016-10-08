package com.crakama.refugee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crakama.refugee.BackPressImpl;
import com.crakama.refugee.OnBackPressListener;
import com.crakama.refugee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragment extends Fragment implements OnBackPressListener {


    public RootFragment() {
        // Required empty public constructor
    }


    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }

}
