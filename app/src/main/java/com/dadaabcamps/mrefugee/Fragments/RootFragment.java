package com.dadaabcamps.mrefugee.Fragments;


import android.support.v4.app.Fragment;

import com.dadaabcamps.mrefugee.BackPressImpl;
import com.dadaabcamps.mrefugee.OnBackPressListener;

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
