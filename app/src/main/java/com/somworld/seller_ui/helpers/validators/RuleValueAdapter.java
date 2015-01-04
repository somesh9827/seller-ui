package com.somworld.seller_ui.helpers.validators;

import com.somworld.seller_ui.helpers.validators.rules.RULE;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */

public final class RuleValueAdapter<DATA_TYPE> {
    final int mId;
    final DATA_TYPE mValue;
    final List<RuleErrorPair> rules;

   public  RuleValueAdapter(int id, DATA_TYPE value) {
       mId = id;
       mValue = value;
       rules = new ArrayList<RuleErrorPair>();
    }

    public RuleValueAdapter<DATA_TYPE> addRule(final RULE newRule,final String errorMessage) {
        if(newRule == null || errorMessage == null) throw new IllegalArgumentException("Invalid Argument.");
        RuleErrorPair ruleErrorPair= new RuleErrorPair(newRule,errorMessage);
        rules.add(ruleErrorPair);
        return this;
    }

    public int getId() {
        return mId;
    }

    public List<RuleErrorPair> getAllRules() {
        return Collections.unmodifiableList(rules);
    }

    public DATA_TYPE getValue(){
        return mValue;
    }


}

