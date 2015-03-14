package com.somworld.seller_ui.models;


/**
 * Created by somesh.shrivastava on 08/02/15.
 */
public class LoginDetail {

  private String email;

  private String password;

  private String contact;

  public LoginDetail() {
    email = null;
    password = null;
    contact = null;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }
}
