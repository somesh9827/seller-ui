package com.somworld.seller_ui.helpers.validators;

import java.util.List;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class Validator implements IValidators {
    private ValidationError error= null;
    public enum VALIDATION_MODE  {IMEDIAT,BURST};
    private VALIDATION_MODE validationMode;
    private IValidatorListener mValidationListener;

    public  Validator(IValidatorListener listener){
        mValidationListener = listener;
    }

    public Validator() {
        mValidationListener = null;
    }

    public void setValidationMode(VALIDATION_MODE mode) {
        validationMode = mode;
    }

    @Override
    public void validate(List<RuleValueAdapter> fieldsForValidation) {
        error = new ValidationError();
        boolean isCorrect = true;
        if(fieldsForValidation == null) throw new IllegalArgumentException("Null Argument passed");
        for(RuleValueAdapter field:fieldsForValidation) {
            if(field == null) throw new IllegalArgumentException("Null Validation field passed");
                List<RuleErrorPair> rules = field.getAllRules();
                if(rules == null) throw new IllegalArgumentException("Null Validation field passed");
                for(RuleErrorPair rule : rules) {
                    if(!rule.getRule().isValid(field.getValue())) {
                        error.appendErrorMessage(field.getId(),rule.getErrorMessage());
                        isCorrect = false;
                    }
                }
        }

        if(isCorrect) mValidationListener.onValidationSuccess(fieldsForValidation);
        else mValidationListener.onValidationFail(error);
    }


    @Override
    public ValidationError getValidationError() {
        return error;
    }
}
