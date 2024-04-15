package com.SpringSecurityUpdated.pojo;

import lombok.Data;

@Data
public class PasswordDto {
    private String currentPassword;
    private String newPassword;
}
