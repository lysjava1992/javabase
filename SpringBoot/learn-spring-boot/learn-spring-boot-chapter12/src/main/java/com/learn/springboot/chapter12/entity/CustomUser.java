package com.learn.springboot.chapter12.entity;

import com.learn.springboot.chapter12.annotation.IsMobile;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
@Data
public class CustomUser {
    private Long id;
    @NotNull
    private String name;
    @Min(0)
    private Integer age;
    @Email
    private String email;
    @Past
    private Date birthday;
    @IsMobile
    private String phone;
}
