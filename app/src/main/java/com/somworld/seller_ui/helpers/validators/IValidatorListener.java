package com.somworld.seller_ui.helpers.validators;

import java.util.List;

/**
 * Created by somesh.shrivastava on 04/01/15.
 */
public interface IValidatorListener {

    public void onValidationFail(ValidationError error);

    public void onValidationSuccess(List<RuleValueAdapter> ruleValueAdapters);

}
