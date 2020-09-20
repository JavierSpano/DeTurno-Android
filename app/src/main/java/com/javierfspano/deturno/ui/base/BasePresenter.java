package com.javierfspano.deturno.ui.base;

public abstract class BasePresenter<V> {

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }
}
