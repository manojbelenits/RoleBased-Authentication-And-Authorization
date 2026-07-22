package org.belen.rolebasedaccess.reqDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String msg;
    private String toekn;

}
