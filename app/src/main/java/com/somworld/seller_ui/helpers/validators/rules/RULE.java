package com.somworld.seller_ui.helpers.validators.rules;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public interface RULE<VALIDATABLE> {

    public boolean isValid(VALIDATABLE validatable);

}
