package com.javierfspano.deturno.di;

import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.repository.PharmacyRepository;
import com.javierfspano.deturno.ui.MainContract;
import com.javierfspano.deturno.ui.MainPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class MainActivityModule {
    @Provides
    static MainContract.Presenter provideMainPresenter(GetPharmacyListUseCase getPharmacyListUseCase) {
        return new MainPresenter(getPharmacyListUseCase);
    }

    @Provides
    static GetPharmacyListUseCase provideGetPharmacyListUseCase(PharmacyRepository pharmacyRepository) {
        return new GetPharmacyListUseCase(pharmacyRepository);
    }
}
