package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int user_id;
    private String user_tel;
    private String display_name;
    private String user_password;
    private String user_introduce;
    private String user_place;
    private String user_photo;
    private int user_fans;
    private int user_follow;
    private String registration_time;
    private int user_privilege;

}
