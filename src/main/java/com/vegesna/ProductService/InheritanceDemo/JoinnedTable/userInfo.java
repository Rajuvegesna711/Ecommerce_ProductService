package com.vegesna.ProductService.InheritanceDemo.JoinnedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="JoinnedTable_userInfo")
public class userInfo extends user{
    private String firstName;
    private String lastName;
    @PrimaryKeyJoinColumn
    private Long user_Id;
    private String address;
    private String mobileNo;
}
