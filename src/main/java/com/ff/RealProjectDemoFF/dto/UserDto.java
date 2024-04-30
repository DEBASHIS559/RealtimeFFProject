package com.ff.RealProjectDemoFF.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserDto {

    private String name;

    private String privilege;

    private  String email;

    private long phone_number;


}
