package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.helpers.validators.IValidators;
import com.somworld.seller_ui.helpers.validators.commons.validator.routines.IntegerValidator;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class MinInteger implements RULE<Integer> {
    int minInt = 0;

    public MinInteger(int min) {
        minInt = min;
    }
    @Override
    public boolean isValid(Integer value) {
        if(value == null) throw new IllegalArgumentException("Not a number");
        return IntegerValidator.getInstance().minValue(value,minInt);

    }
}
