package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.helpers.validators.RuleValueAdapter;
import com.somworld.seller_ui.helpers.validators.rules.MaxInteger;
import com.somworld.seller_ui.helpers.validators.rules.NotEmpty;
import com.somworld.seller_ui.helpers.validators.rules.RULE;

import junit.framework.TestCase;

/**
 * Created by somesh.shrivastava on 03/01/15.
 */
public class RuleValueAdapterTest extends TestCase {

    RuleValueAdapter<? extends Object> ruleValueAdapter;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ruleValueAdapter = new RuleValueAdapter<Integer>(1,10);

    }

    public void testAddRulePassWhenRulesAndErrorMessageCorrect() {
       try {
           RULE<Object> notEmptyRule = new NotEmpty();
           RULE<Integer> max_rule = new MaxInteger(10);
           ruleValueAdapter.addRule(notEmptyRule, "Should Not Be null").addRule(max_rule, "Should be less than 10");
       }catch(Exception e){
           fail(e.getMessage());
       }

    }

    public void testAddRuleFailWhenRulesisNull() {
        try {
            RULE<Object> notEmptyRule = new NotEmpty();
            RULE<Integer> max_rule = new MaxInteger(10);
            ruleValueAdapter.addRule(null, "Should Not Be null").addRule(max_rule, "Should be less than 10");
            fail("Rule Should Not be Null");
        }catch(Exception e){
            assertEquals(true,true);
        }

    }

    public void testAddRuleFailWhenErrorIsNullNull() {
        try {
            RULE<Object> notEmptyRule = new NotEmpty();
            RULE<Integer> max_rule = new MaxInteger(10);
            ruleValueAdapter.addRule(notEmptyRule, null).addRule(max_rule, "Should be less than 10");
            fail("Error Should Not be Null");
        }catch(Exception e){
            assertEquals(true,true);
        }

    }






}
