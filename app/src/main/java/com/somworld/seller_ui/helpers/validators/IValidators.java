package com.somworld.seller_ui.helpers.validators;

import java.util.List;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public interface IValidators {

    public void validate(List<RuleValueAdapter> field);

    public ValidationError getValidationError();

}
