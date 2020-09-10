package com.javierfspano.deturno.base;

public abstract class BasePresenter<V> {

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }
}
