package com.example.sampleforwebfluxwithsecurityjwt.vo.v1.response;

import com.example.sampleforwebfluxwithsecurityjwt.constant.ResponseStatus;
import com.example.sampleforwebfluxwithsecurityjwt.vo.common.response.BasicResponse;
import lombok.*;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(of = "token", callSuper = false)
public class AuthResponse extends BasicResponse {
    private String token;

    public AuthResponse(ResponseStatus responseStatus, String token) {
        super(responseStatus);
        this.token = token;
    }
}