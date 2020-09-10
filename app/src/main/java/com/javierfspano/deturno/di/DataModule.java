package com.javierfspano.deturno.di;

import com.javierfspano.deturno.repository.PharmacyRepository;
import com.javierfspano.deturno.repository.PharmacyRepositoryImpl;
import com.javierfspano.deturno.service.PharmacyService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataModule {

    @Provides
    static PharmacyRepository providePharmacyRepository(PharmacyService pharmacyService) {
        return new PharmacyRepositoryImpl(pharmacyService);
    }

}
