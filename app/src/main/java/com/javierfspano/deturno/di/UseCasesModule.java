package com.javierfspano.deturno.di;

import com.javierfspano.deturno.domain.GetIdTokenUseCase;
import com.javierfspano.deturno.domain.GetPharmaciesByCoordinatesUseCase;
import com.javierfspano.deturno.domain.GetPharmaciesByTextUseCase;
import com.javierfspano.deturno.repository.PharmacyRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class UseCasesModule {

    @Provides
    static GetPharmaciesByTextUseCase provideGetPharmaciesByTextUseCase(PharmacyRepository pharmacyRepository) {
        return new GetPharmaciesByTextUseCase(pharmacyRepository);
    }

    @Provides
    static GetPharmaciesByCoordinatesUseCase provideGetPharmaciesByCoordinatesUseCase(PharmacyRepository pharmacyRepository) {
        return new GetPharmaciesByCoordinatesUseCase(pharmacyRepository);
    }

    @Provides
    static GetIdTokenUseCase provideGetIdTokenUseCase() {
        return new GetIdTokenUseCase();
    }
}
