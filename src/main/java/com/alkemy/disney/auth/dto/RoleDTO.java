package com.alkemy.disney.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RoleDTO {

    @NotNull
    @NotEmpty
    String Role;
}
