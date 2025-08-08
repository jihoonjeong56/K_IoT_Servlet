package org.example.k4_iot_servlet.entity;


import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private String country;
}
