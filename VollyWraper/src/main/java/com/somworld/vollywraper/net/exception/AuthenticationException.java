package com.somworld.vollywraper.net.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by somesh.shrivastava on 28/03/15.
 */
@Getter
@Setter
@NoArgsConstructor

public class AuthenticationException extends Exception {
  @Accessors(prefix = "m")
  int mCode;
  public AuthenticationException(int code,String message) {
    super(message);
    mCode = code;
  }

}
