package com.javierfspano.deturno.ui.splashscreen;

import com.javierfspano.deturno.domain.GetIdTokenUseCase;
import com.javierfspano.deturno.ui.base.BasePresenter;
import com.javierfspano.deturno.ui.locationinput.LocationInputActivity;
import com.javierfspano.deturno.util.GenericServiceCallback;

public class SplashScreenPresenter extends BasePresenter<SplashScreenContract.View> implements SplashScreenContract.Presenter {

    private GetIdTokenUseCase getIdTokenUseCase;

    public SplashScreenPresenter(GetIdTokenUseCase getIdTokenUseCase) {
        this.getIdTokenUseCase = getIdTokenUseCase;
    }

    @Override
    public void onCreate() {
        getIdToken();
    }

    @Override
    public void onRetry() {
        getIdToken();
    }

    private void getIdToken() {
        getIdTokenUseCase.execute(new GenericServiceCallback<String>() {
            @Override
            public void onSuccess(String idToken) {
                view.goToNextActivity(idToken, LocationInputActivity.class);
            }

            @Override
            public void onError(Throwable throwable) {
                view.showTokenError();
            }
        });
    }
}
