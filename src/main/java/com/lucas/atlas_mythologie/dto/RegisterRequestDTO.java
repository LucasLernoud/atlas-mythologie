package com.lucas.atlas_mythologie.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String username;
    private String password;
    private String confirmPassword;

}