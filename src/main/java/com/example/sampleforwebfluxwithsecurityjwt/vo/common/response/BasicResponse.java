package com.example.sampleforwebfluxwithsecurityjwt.vo.common.response;

import com.example.sampleforwebfluxwithsecurityjwt.constant.ResponseStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

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
@EqualsAndHashCode(of = "code")
public class BasicResponse implements Serializable {
    private Integer code;
    private String description;
    private Date createdAt;

    public BasicResponse(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.description = responseStatus.getDescription();
        this.createdAt = new Date();
    }
}
