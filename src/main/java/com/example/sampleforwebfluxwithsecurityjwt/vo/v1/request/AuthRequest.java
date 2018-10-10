package com.example.sampleforwebfluxwithsecurityjwt.vo.v1.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-11
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
public class AuthRequest implements Serializable {
    private String username;
    private String password;
}