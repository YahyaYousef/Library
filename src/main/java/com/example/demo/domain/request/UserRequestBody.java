package com.example.demo.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestBody {

    private String fullName;
    private String email;
    private String username;
    private String phone;
    private String password;

}
