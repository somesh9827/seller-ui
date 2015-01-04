package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.helpers.validators.commons.validator.routines.IntegerValidator;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class MaxInteger implements RULE<Integer> {
    int maxInt;
    public MaxInteger(int max) {
        maxInt = max;
    }
    @Override
    public boolean isValid(Integer value) {
        if(value == null) throw new IllegalArgumentException("Not a number");
        return IntegerValidator.getInstance().maxValue(value,maxInt);
    }
}
