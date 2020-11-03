package com.javierfspano.deturno;

import com.google.android.gms.maps.model.LatLng;
import com.javierfspano.deturno.ui.main.map.PharmacyMapFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class PharmacyMapPresenterTest  {


    private PharmacyMapFragment fragment;


    @Mock
    LatLng latLng;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        fragment = new PharmacyMapFragment();
    }

    @Test
    public void testCenterMapOnCreate(){
        fragment.centerMap(latLng);

    }
}
