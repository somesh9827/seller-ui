package com.somworld.seller_ui.models.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by somesh.shrivastava on 01/03/15.
 */
@Getter
@Setter
public class Register1DTO {

  private MailAndPasswordDTO loginDetail =null;

  private AddressDTO shopAddress = null;

  private ShopNameDTO shopDetail = null;



}
