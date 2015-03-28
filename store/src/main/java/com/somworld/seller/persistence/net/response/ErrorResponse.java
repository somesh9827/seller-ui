package com.somworld.seller.persistence.net.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
@Getter
@Setter
public class ErrorResponse {

  int status;

  String message;

  public ErrorResponse(int status,String message) {
    this.status = status;
    this.message = message;
  }

}
