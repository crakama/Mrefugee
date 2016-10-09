package com.crakama.refugee.Adapters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.crakama.refugee.Fragments.LiveNewsFrag;
import com.crakama.refugee.Fragments.NoticeBoardFrag;
import com.crakama.refugee.Fragments.RepatriationRootFrag;

/**
 * Created by User on 10/8/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Home", "Repatriation", "Camp Notice Board"};
    private final Resources resources;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    //Constructor class
    public ViewPagerAdapter(final Resources resources, FragmentManager fm) {
        super(fm);
        this.resources = resources;
    }



    //get card fragment to display the cards(items) when different tabs are swipped
    @Override
    public Fragment getItem(int position) {
        final Fragment result;
        switch (position) {
            case 0:
                //return LiveNewsFrag.newInstance(position);
                result = new LiveNewsFrag();
                break;
            case 1:

//                return RepatriationRootFrag.newInstance(position);
                result = new RepatriationRootFrag();
                break;
            case 2:
                //return NoticeBoardFrag.newInstance(position);
                result = new NoticeBoardFrag();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }


    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }


    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}//End ClassPagerAdapter class