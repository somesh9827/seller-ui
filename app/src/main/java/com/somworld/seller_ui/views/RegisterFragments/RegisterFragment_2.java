package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class RegisterFragment_2 extends RegisterFragment {

  private EditText shopName, ownerFirstName, ownerLastName;

  @Override
  protected String getTitle() {
    return  getActivity().getString(R.string.shopInfoTitle);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_2, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    shopName = (EditText) v.findViewById(R.id.shop_name);
    ownerFirstName = (EditText) v.findViewById(R.id.owner_first_name);
    ownerLastName = (EditText) v.findViewById(R.id.owner_last_name);
    setUp();
    return v;
  }

  @Override
  protected RegistrationDTO getCurrentFragmentData() {
    ShopNameDTO shopNameDTO = new ShopNameDTO();
    shopNameDTO.setShopName(shopName.getText().toString());
    shopNameDTO.setOwnerFirstName(ownerFirstName.getText().toString());
    shopNameDTO.setOwnerLastName(ownerLastName.getText().toString());
    return shopNameDTO;
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO registrationDTO) {
    ShopNameDTO shopNameDTO = (ShopNameDTO)registrationDTO;
    shopName.setText(shopNameDTO.getShopName());
    ownerFirstName.setText(shopNameDTO.getOwnerFirstName());
    ownerLastName.setText(shopNameDTO.getOwnerLastName());
  }
}
