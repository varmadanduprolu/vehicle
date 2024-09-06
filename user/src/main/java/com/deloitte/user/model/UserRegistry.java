package com.deloitte.user.model;


import lombok.Data;

@Data
public class UserRegistry {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    private String confirmationPassword;


}
