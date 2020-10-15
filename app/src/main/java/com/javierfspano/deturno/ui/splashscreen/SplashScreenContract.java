package com.javierfspano.deturno.ui.splashscreen;

import android.app.Activity;

import com.javierfspano.deturno.ui.base.BaseContract;

public interface SplashScreenContract {

    interface View extends BaseContract.BaseView {

        void goToNextActivity(String idToken, Class<? extends Activity> activity);

        void showTokenError();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void onCreate();

        void onRetry();
    }
}
