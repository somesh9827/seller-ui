package com.somworld.seller_ui.helpers.validators;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class ValidationError {

    private Map<Integer,List<String>> errors = Collections.EMPTY_MAP;
    private final List<String> errorMessages = Collections.EMPTY_LIST;

    public ValidationError() {
        errors = new HashMap<Integer, List<String>>();

    }

    public ValidationError appendErrorMessage(int id,String errorMessage){
        if(errors.containsKey(id))
            errors.get(id).add(errorMessage);
        else {
            final List<String> errorMessages = new ArrayList<String>();
            errorMessages.add(errorMessage);
            errors.put(id,errorMessages);
        }
        return this;
    }

    public Vector<Integer> getAllKeys() {
        Vector<Integer> keys = new Vector<Integer>();
        for(Integer key : errors.keySet()){
            keys.add(key);
        }
        return keys;
    }

    public List<String> getErrorMessage(int id) {
        return  Collections.unmodifiableList(errors.get(id));
    }

    public String getConcatinatedErrorMessage(int id) {
        StringBuilder error_message = new StringBuilder();
        List<String> errorMessages = errors.get(id);
        for (String error:errorMessages) {
            if(error.trim().length() > 0)
                error_message.append(error).append("\n");
        }

        return error_message.toString();
    }

    public String getFirstErrorMessage(int id) {
        List<String> errorMessages = errors.get(id);
        return errorMessages.get(0);
    }
}
