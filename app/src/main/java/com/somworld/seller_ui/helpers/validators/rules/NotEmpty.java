package com.somworld.seller_ui.helpers.validators.rules;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class NotEmpty implements RULE<Object> {

    @Override
    public boolean isValid(Object obj)
    {  if(obj == null) return false;
        String s = obj.toString();
        return s != null && s.length() > 0;
    }
}
