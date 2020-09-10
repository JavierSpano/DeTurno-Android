package com.javierfspano.deturno.util;

public interface GenericServiceCallback<T> {

    void onSuccess(T t);

    void onError(Throwable throwable);
}
