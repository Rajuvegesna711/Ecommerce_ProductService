package com.vegesna.scalerproject.InheritanceDemo.JoinnedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity(name="joinnedTable_User")
@Inheritance(strategy = InheritanceType.JOINED)
public class user {
    @Id
    private long Id;
    private String email;
    private String password;

}
