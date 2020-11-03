package com.javierfspano.deturno.di;

import com.javierfspano.deturno.domain.GetIdTokenUseCase;
import com.javierfspano.deturno.domain.GetPharmaciesByCoordinatesUseCase;
import com.javierfspano.deturno.domain.GetPharmaciesByTextUseCase;
import com.javierfspano.deturno.ui.locationinput.LocationInputContract;
import com.javierfspano.deturno.ui.locationinput.LocationInputPresenter;
import com.javierfspano.deturno.ui.main.MainContract;
import com.javierfspano.deturno.ui.main.MainPresenter;
import com.javierfspano.deturno.ui.main.list.PharmacyListContract;
import com.javierfspano.deturno.ui.main.list.PharmacyListPresenter;
import com.javierfspano.deturno.ui.main.map.PharmacyMapContract;
import com.javierfspano.deturno.ui.main.map.PharmacyMapPresenter;
import com.javierfspano.deturno.ui.splashscreen.SplashScreenContract;
import com.javierfspano.deturno.ui.splashscreen.SplashScreenPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class PresentersModule {

    @Provides
    static MainContract.Presenter provideMainPresenter(GetPharmaciesByTextUseCase getPharmaciesByTextUseCase, GetPharmaciesByCoordinatesUseCase getPharmaciesByCoordinatesUseCase) {
        return new MainPresenter(getPharmaciesByTextUseCase, getPharmaciesByCoordinatesUseCase);
    }

    @Provides
    static SplashScreenContract.Presenter provideSplashScreenPresenter(GetIdTokenUseCase getIdTokenUseCase) {
        return new SplashScreenPresenter(getIdTokenUseCase);
    }

    @Provides
    static LocationInputContract.Presenter provideLocationInputPresenter() {
        return new LocationInputPresenter();
    }

    @Provides
    static PharmacyMapContract.Presenter providePharmacyMapPresenter() {
        return new PharmacyMapPresenter();
    }

    @Provides
    static PharmacyListContract.Presenter providePharmacyListPresenter() {
        return new PharmacyListPresenter();
    }
}
