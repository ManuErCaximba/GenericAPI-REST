package com.generic.ecommerce.main.dto.request;

import com.generic.ecommerce.main.dto.model.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDTO {

    @NotNull
    @Valid
    private UserDTO user;

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
