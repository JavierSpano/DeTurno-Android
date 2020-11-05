package com.javierfspano.deturno.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.R;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.ui.main.list.PharmacyListContract;
import com.javierfspano.deturno.ui.main.list.PharmacyListFragment;
import com.javierfspano.deturno.ui.main.map.PharmacyMapContract;
import com.javierfspano.deturno.ui.main.map.PharmacyMapFragment;
import com.javierfspano.deturno.util.MapReadyListener;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context context;

    private PharmacyMapFragment mapFragment;
    private PharmacyListFragment listFragment;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            if (listFragment == null) {
                listFragment = PharmacyListFragment.newInstance();
            }
            return listFragment;

        } else {

            if (mapFragment == null) {
                mapFragment = PharmacyMapFragment.newInstance((MapReadyListener) context);
            }
            return mapFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }


    public void addMarker(MarkerOptions markerOptions) {
        if (mapFragment != null) {
            ((PharmacyMapContract.View) mapFragment).addMarker(markerOptions);
        }
    }

    public void centerMap(LatLng latLng) {
        if (mapFragment != null) {
            ((PharmacyMapContract.View) mapFragment).centerMap(latLng);
        }
    }

    public void clearMapMarkers() {
        if (mapFragment != null) {
            ((PharmacyMapContract.View) mapFragment).clearMapMarkers();
        }
    }

    public void updateList(List<Pharmacy> list) {
        if (listFragment != null) {
            ((PharmacyListContract.View) listFragment).setList(list);
        }
    }
}