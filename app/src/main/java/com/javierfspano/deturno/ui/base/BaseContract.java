package com.javierfspano.deturno.ui.base;

import android.content.Context;

public interface BaseContract {

    interface BaseView {

        Context getContext();
    }

    interface BasePresenter<V extends BaseView> {

        void attachView(V view);
    }
}
