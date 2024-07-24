package org.example.bean;

import lombok.Data;

//lombok
@Data
public class UserInfo {
    private String firstName;
    private String lastName;

    public UserInfo(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
