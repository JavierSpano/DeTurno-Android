package com.javierfspano.deturno.di;

import com.javierfspano.deturno.domain.GetIdTokenUseCase;
import com.javierfspano.deturno.domain.GetPharmacyListUseCase;
import com.javierfspano.deturno.repository.PharmacyRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class UseCasesModule {

    @Provides
    static GetPharmacyListUseCase provideGetPharmacyListUseCase(PharmacyRepository pharmacyRepository) {
        return new GetPharmacyListUseCase(pharmacyRepository);
    }

    @Provides
    static GetIdTokenUseCase provideGetIdTokenUseCase() {
        return new GetIdTokenUseCase();
    }
}
