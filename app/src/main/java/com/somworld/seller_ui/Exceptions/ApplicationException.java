package com.somworld.seller_ui.Exceptions;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public class ApplicationException extends Exception {

    private final  String mMessage;

    public ApplicationException(String message) {
        super();
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }


}
