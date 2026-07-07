package com.itheima.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

//自动生成setter getter ...
@Data
public class User {

    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore  //让转化为json数据的时候不展示password
    private String password;//密码
    @NotEmpty
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
