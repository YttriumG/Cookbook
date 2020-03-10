package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private int id;
    private String name;
    private String pwd;
    //1为可用，0为停用
    private boolean enable;
}
