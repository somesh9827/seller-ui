package com.somworld.seller_ui.models.dtos;

import android.os.Parcel;

import com.somworld.seller_ui.helpers.ApplicationConstants;

/**
 * Created by somesh.shrivastava on 19/01/15.
 */
public class MailAndPasswordDTO implements RegistrationPageDTO {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null) email = ApplicationConstants.ABSENT;
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

    private String email;

    private String password;

    private String contact;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(contact);
    }

    public static final Creator<MailAndPasswordDTO> creator = new Creator<MailAndPasswordDTO>() {
        @Override
        public MailAndPasswordDTO createFromParcel(Parcel parcel) {
            MailAndPasswordDTO mailAndPasswordDTO = new MailAndPasswordDTO();
            mailAndPasswordDTO.setEmail(parcel.readString());
            mailAndPasswordDTO.setPassword(parcel.readString());
            mailAndPasswordDTO.setContact(parcel.readString());
            return mailAndPasswordDTO;
        }

        @Override
        public MailAndPasswordDTO[] newArray(int size) {
            return new MailAndPasswordDTO[size];
        }
    };
}
