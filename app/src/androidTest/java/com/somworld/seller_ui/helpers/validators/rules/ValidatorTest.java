package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.Validator;
import com.somworld.seller_ui.helpers.validators.IValidatorListener;


import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 04/01/15.
 */
public class ValidatorTest extends TestCase {

    List<RuleValueAdapter> ruleValueAdapters;
    IValidatorListener listener;
    @Override
    public void setUp() throws Exception {
        super.setUp();
       // listener =  mock(IValidatorListener.class);

        //verify(listener,times(1)).onValidationSuccess();

        ruleValueAdapters= new ArrayList<RuleValueAdapter>();
        RULE notEmptyRule = new NotEmpty();
        RuleValueAdapter ruleValueAdapter1 =  new RuleValueAdapter(1,"Somesh");
        ruleValueAdapter1.addRule(notEmptyRule,"Should not be null");

        RULE notEmptyRule2 = new NotEmpty();
        RULE minRule = new MinInteger(5);
        RuleValueAdapter ruleValueAdapter2 =  new RuleValueAdapter(2,10);
        ruleValueAdapter2.addRule(notEmptyRule2,"Should Not be Null").addRule(minRule,"Should not be less than 5");
        ruleValueAdapters.add(ruleValueAdapter1);
        ruleValueAdapters.add(ruleValueAdapter2);
    }

    public void testValidateShouldPass() throws Exception {
        //listener =  mock(IValidatorListener.class);
        //verify(listener,times(1)).onValidationSuccess();
        Validator validator = new Validator();
        validator.validate(ruleValueAdapters);
        assertEquals(true,true);
    }

    public void testValidationShouldReturnIllegalArgumentException() {
        try {
            Validator validator = new Validator();
            validator.validate(null);
            fail("List should not be null");
        }catch(IllegalArgumentException e){
            assertEquals(true,true);
        }
    }

    public void testValidationShouldReturnIllegalArgumentException2() {

        try {
            Validator validator = new Validator();
            validator.validate(null);
            ruleValueAdapters.add(null);
            fail("Rule Value adapter should not be null");
        }catch(IllegalArgumentException e){
            assertEquals(true,true);
        }
    }}
