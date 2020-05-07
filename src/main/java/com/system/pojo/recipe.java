package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class recipe {
    private int id;
    private String name;
    private String pic_url;
    private String type;
    private String description;
    private String material;
    private String step;
    private String trick;
    private String create_time;
    private String last_modified_time;
    private long comments;
    private long views;
    private long likes;
    private int author;
}


