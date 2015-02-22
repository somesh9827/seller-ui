package com.somworld.seller_ui.helpers.validators.rules;

import com.somworld.seller_ui.helpers.validators.commons.validator.routines.EmailValidator;

/**
 * Created by somesh.shrivastava on 21/02/15.
 */
public class EmailRule implements RULE<String> {

  @Override
  public boolean isValid(String email) {
    if(email == null)
      return false;
    return EmailValidator.getInstance().isValid(email);

  }
}
