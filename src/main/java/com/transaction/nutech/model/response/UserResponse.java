package com.transaction.nutech.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String profileImage;
}
