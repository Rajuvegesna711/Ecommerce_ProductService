package com.vegesna.scalerproject.InheritanceDemo.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="SingleTable_userInfo")
@DiscriminatorValue("1")
public class userInfo extends user {
    private String firstName;
    private String lastName;
    private Long user_Id;
    private String address;
    private String mobileNo;
}
