package com.javierfspano.deturno.exception;

import okhttp3.ResponseBody;

public class PharmacyServiceException extends Throwable {
    private ResponseBody errorBody;

    public PharmacyServiceException(ResponseBody errorBody) {
        this.errorBody = errorBody;
    }

    public ResponseBody getErrorBody() {
        return errorBody;
    }
}
