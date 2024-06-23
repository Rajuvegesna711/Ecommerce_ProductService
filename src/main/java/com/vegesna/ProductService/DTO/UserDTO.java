package com.vegesna.ProductService.DTO;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDTO {
    private String fname;
    private String lname;
    private String email;
    private List<Roles> roles;

}
