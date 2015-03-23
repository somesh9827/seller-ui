package com.somworld.seller_ui.models.dtos;

import com.somworld.seller_ui.models.Location;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by somesh.shrivastava on 14/03/15.
 */
@Getter
@Setter
public class ShopLocationDTO {

  private int shopID;
  private Location shopLocation;
}
