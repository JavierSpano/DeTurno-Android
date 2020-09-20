package com.javierfspano.deturno;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javierfspano.deturno.data.Coordinates;
import com.javierfspano.deturno.data.Pharmacy;
import com.javierfspano.deturno.data.PharmacyServiceResponse;
import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.ui.main.MainContract;
import com.javierfspano.deturno.ui.main.MainPresenter;
import com.javierfspano.deturno.util.GenericServiceCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Captor
    private ArgumentCaptor<GenericServiceCallback<PharmacyServiceResponse>> callbackArgumentCaptor;

    @Mock
    private GetPharmacyListUseCase getPharmacyListUseCase;

    @Mock
    private PharmacyServiceResponse pharmacyServiceResponse;

    @Mock
    private Pharmacy pharmacy;

    @Mock
    private Coordinates coordinates;

    @Mock
    private MainContract.View view;

    private MainPresenter mainPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(getPharmacyListUseCase);
        mainPresenter.attachView(view);

    }

    @Test
    public void testMarkersAreAddedWhenMapIsReady() {
        String randomLatOrLong = "-30";
        when(pharmacyServiceResponse.getPharmacies()).thenReturn(Collections.singletonList(pharmacy));
        when(pharmacyServiceResponse.getMapCenter()).thenReturn(coordinates);
        when(coordinates.getLat()).thenReturn(randomLatOrLong);
        when(coordinates.getLng()).thenReturn(randomLatOrLong);
        when(pharmacy.getLat()).thenReturn(randomLatOrLong);
        when(pharmacy.getLng()).thenReturn(randomLatOrLong);

        mainPresenter.onMapReady();
        verify(getPharmacyListUseCase).execute(any(), callbackArgumentCaptor.capture());
        callbackArgumentCaptor.getValue().onSuccess(pharmacyServiceResponse);
        verify(view).addMarker(any(MarkerOptions.class));
    }

    @Test
    public void testMapIsCenteredWhenMapIsReady() {
        String randomLatOrLong = "-30";
        when(pharmacyServiceResponse.getPharmacies()).thenReturn(Collections.singletonList(pharmacy));
        when(pharmacyServiceResponse.getMapCenter()).thenReturn(coordinates);
        when(coordinates.getLat()).thenReturn(randomLatOrLong);
        when(coordinates.getLng()).thenReturn(randomLatOrLong);
        when(pharmacy.getLat()).thenReturn(randomLatOrLong);
        when(pharmacy.getLng()).thenReturn(randomLatOrLong);
        mainPresenter.onMapReady();
        verify(getPharmacyListUseCase).execute(any(), callbackArgumentCaptor.capture());
        callbackArgumentCaptor.getValue().onSuccess(pharmacyServiceResponse);
        verify(view).centerMap(any(LatLng.class));
    }
}
