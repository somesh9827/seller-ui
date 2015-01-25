package com.somworld.seller_ui.views.RegisterFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.somworld.seller_ui.R;
import com.somworld.seller_ui.models.dtos.AddressDTO;
import com.somworld.seller_ui.models.dtos.RegistrationDTO;

/**
 * Created by somesh.shrivastava on 24/01/15.
 */
public class RegisterFragment_3 extends RegisterFragment {

  private EditText addressLine1, addressLine2,area,city,state,pincode;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.register_fragment_3, container, false);
    nextButton = (Button) v.findViewById(R.id.next_button);
    backButton = (Button) v.findViewById(R.id.previous_button);
    addressLine1 = (EditText)v.findViewById(R.id.address_line_1);
    addressLine2 = (EditText)v.findViewById(R.id.address_line_2);
    area = (EditText)v.findViewById(R.id.area);
    city = (EditText)v.findViewById(R.id.city);
    state = (EditText)v.findViewById(R.id.state);
    pincode = (EditText)v.findViewById(R.id.pincode);
    setUp();
    return v;
  }

  @Override
  protected RegistrationDTO getCurrentFragmentData() {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setAddressLine1(addressLine1.getText().toString());
    addressDTO.setAddressLine2(addressLine2.getText().toString());
    addressDTO.setArea(area.getText().toString());
    addressDTO.setCity(city.getText().toString());
    addressDTO.setState(state.getText().toString());
    addressDTO.setPincode(pincode.getText().toString());
    return addressDTO;
  }

  @Override
  protected String getTitle() {
    return  getActivity().getString(R.string.shopAddressTitle);
  }

  @Override
  protected void setCurrentFragmentData(RegistrationDTO registrationDTO) {
    AddressDTO addressDTO = (AddressDTO)registrationDTO;
    addressLine1.setText(addressDTO.getAddressLine1());
    addressLine2.setText(addressDTO.getAddressLine2());
    area.setText(addressDTO.getArea());
    city.setText(addressDTO.getCity());
    state.setText(addressDTO.getState());
    pincode.setText(addressDTO.getPincode());
  }
}
