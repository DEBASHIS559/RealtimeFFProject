package com.ff.RealProjectDemoFF.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("user")
public class User extends BaseEntity{

    @Id
    private String id;

    private String name;

    private String privilege;

    private  int no_of_tickets ;

  //  private LocalDateTime created_on;

    private String createdTime;
    private String status;

    @Indexed(unique = true)
    private  String email;

    @Indexed(unique = true)
    private long phone_number;

    @Indexed(unique = true)
    private String token;

    private String password;

}
