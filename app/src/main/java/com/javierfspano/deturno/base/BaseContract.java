package com.javierfspano.deturno.base;

public interface BaseContract {

    interface BaseView {

    }

    interface BasePresenter<V extends BaseView> {

        void attachView(V view);
    }
}
