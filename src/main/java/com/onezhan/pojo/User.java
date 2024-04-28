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
    Integer id;
    String username;
    @JsonIgnore
    String password;
    String nickname;
    String email;
    String userImg;
    LocalDateTime updateTime;
    LocalDateTime createTime;
}
