package com.vegesna.scalerproject.InheritanceDemo.TableSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="TablePerClass_userInfo")
public class userInfo extends user {
    private String firstName;
    private String lastName;
    private Long user_Id;
    private String address;
    private String mobileNo;
}
