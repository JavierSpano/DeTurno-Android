package com.javierfspano.deturno.di;

import com.javierfspano.deturno.domain.GetIdTokenUseCase;
import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.ui.main.MainContract;
import com.javierfspano.deturno.ui.main.MainPresenter;
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
    static MainContract.Presenter provideMainPresenter(GetPharmacyListUseCase getPharmacyListUseCase) {
        return new MainPresenter(getPharmacyListUseCase);
    }

    @Provides
    static SplashScreenContract.Presenter provideSplashScreenPresenter(GetIdTokenUseCase getIdTokenUseCase) {
        return new SplashScreenPresenter(getIdTokenUseCase);
    }
}
