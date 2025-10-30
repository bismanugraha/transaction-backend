package com.transaction.nutech.model.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UserRequest {
    private int id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
}
