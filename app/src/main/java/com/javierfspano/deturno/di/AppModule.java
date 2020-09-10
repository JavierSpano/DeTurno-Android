package com.javierfspano.deturno.di;

import com.javierfspano.deturno.BuildConfig;
import com.javierfspano.deturno.service.PharmacyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofitClient() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .build();
    }

    @Provides
    @Singleton
    static PharmacyService providePharmacyService(Retrofit retrofit) {
        return retrofit.create(PharmacyService.class);
    }

}
