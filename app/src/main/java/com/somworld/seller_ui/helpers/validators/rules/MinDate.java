package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.R;

import java.util.Date;

/**
 * Created by somesh.shrivastava on 05/01/15.
 */
public class MinDate implements RULE<Date> {
    Date mMinDate;

    public MinDate(Date minDate) {
        mMinDate = minDate;
    }
    @Override
    public boolean isValid(Date currentDate) {
        if(currentDate == null || mMinDate == null) throw new IllegalArgumentException("Date should not be null ");
            if(currentDate.getTime() >= mMinDate.getTime()) return true;
        return false;
    }
}
