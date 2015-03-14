package com.somworld.seller_ui.dataService;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
@Getter
@Setter
public class DataServiceSuccessResponse {

  private int code;
  private String message;
  private Object data;

}
