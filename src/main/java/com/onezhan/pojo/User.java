package com.onezhan.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String userImg;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Integer power;
}
