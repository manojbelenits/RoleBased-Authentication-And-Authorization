package org.belen.rolebasedaccess.reqDto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterRequest {

    private String username;

    private String password;

}