package com.somworld.seller_ui.helpers.validators;

import com.somworld.seller_ui.helpers.validators.rules.RULE;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class RuleErrorPair<DATA_TYPE> {
    final RULE<DATA_TYPE> mRule;
    final String mErrorMessage;

    RuleErrorPair(RULE<DATA_TYPE> rule,String errorMessage) {

        mRule = rule;
        mErrorMessage = errorMessage;
    }

    public RULE<DATA_TYPE> getRule(){
        return mRule;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}

