package com.somworld.vollywraper.net.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
@Getter
@Setter
public class ErrorResponse {

  int code;

  String message;

  public ErrorResponse(int status,String message) {
    this.code = status;
    this.message = message;
  }

}
