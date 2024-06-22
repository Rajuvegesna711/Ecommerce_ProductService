package com.vegesna.scalerproject.InheritanceDemo.TableSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity(name="TablePerClass_User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class user {
    @Id
    private long Id;
    private String email;
    private String password;

}
