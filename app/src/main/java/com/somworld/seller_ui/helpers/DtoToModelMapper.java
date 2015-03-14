package com.somworld.seller_ui.helpers;

import com.somworld.seller_ui.models.Address;
import com.somworld.seller_ui.models.LoginDetail;
import com.somworld.seller_ui.models.RegisterModel;
import com.somworld.seller_ui.models.Seller;
import com.somworld.seller_ui.models.Shop;
import com.somworld.seller_ui.models.dtos.AddressDTO;
import com.somworld.seller_ui.models.dtos.MailAndPasswordDTO;
import com.somworld.seller_ui.models.dtos.Register1DTO;
import com.somworld.seller_ui.models.dtos.ShopNameDTO;

/**
 * Created by somesh.shrivastava on 08/02/15.
 */
public class DtoToModelMapper {

  public static final  RegisterModel populateRegisterModel(Register1DTO registerDTO) {
    RegisterModel registerModel = new RegisterModel();
    registerModel.setLoginDetail(populateLoginModel(registerDTO.getLoginDetail()));
    AddressDTO shopAddressDTO = registerDTO.getShopAddress();
    ShopNameDTO shopDetailDTO = registerDTO.getShopDetail();

    Seller seller = new Seller();
    seller.setFirstname(shopDetailDTO.getOwnerFirstName());
    seller.setMiddleName("");
    seller.setLastName(shopDetailDTO.getOwnerLastName());
    seller.setContactNumber(registerDTO.getLoginDetail().getContact());

    Shop shop = new Shop();
    shop.setShopName(shopDetailDTO.getShopName());

    shop.setLocation(null);

    Address shopAddress = new Address();
    shopAddress.setDoorNumber("");
    shopAddress.setAddress1(shopAddressDTO.getAddressLine1());
    shopAddress.setAddress2(shopAddressDTO.getAddressLine2());
    shopAddress.setCity(shopAddressDTO.getCity());
    shopAddress.setArea(shopAddressDTO.getArea());
    shopAddress.setState(shopAddressDTO.getState());
    shopAddress.setPinCode(shopAddressDTO.getPincode());

    shop.setShopAddress(shopAddress);
    seller.setShop(shop);

    registerModel.setSeller(seller);

    return registerModel;
  }

  public static final LoginDetail populateLoginModel(MailAndPasswordDTO loginDTO){
    if(loginDTO == null)
      return null;
    LoginDetail loginDetail = new LoginDetail();
    loginDetail.setContact(loginDTO.getContact());
    loginDetail.setEmail(loginDTO.getEmail());
    loginDetail.setPassword(loginDTO.getPassword());
    return loginDetail;
  }


}
