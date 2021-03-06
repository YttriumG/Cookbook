package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private int user_id;
    private String login_id;
    private String display_name;
    private String pwd;
    //1为可用，0为停用
    private boolean enable;
}
