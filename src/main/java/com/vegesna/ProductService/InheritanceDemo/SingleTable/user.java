package com.vegesna.ProductService.InheritanceDemo.SingleTable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="SingleTable_User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "User_Type",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
public class user {
    @Id
    private long Id;
    private String email;
    private String password;

}
