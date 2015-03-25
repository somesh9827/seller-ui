package com.somworld.seller.persistence.net.resource.Rest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by somesh.shrivastava on 25/03/15.
 */
public class RESTPlaceholder{
  private List<String> values;

  public List<String> getValue() {
    return values;
  }

  public RESTPlaceholder() {

  }

  public RESTPlaceholder add(String placeholder) {
    if(values == null)
      values = new ArrayList<String>();
    values.add(placeholder);
    return this;
  }

}
