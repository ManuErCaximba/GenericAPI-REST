package com.generic.ecommerce.main.dto.response;

import com.generic.ecommerce.main.dto.model.UserDTO;
import java.util.List;

public class RegisterResponseDTO {
    private UserDTO user;
    private List<String> errors;

    public RegisterResponseDTO() {}

    public RegisterResponseDTO(UserDTO user, List<String> errors) {
        this.user = user;
        this.errors = errors;
    }

    public static RegisterResponseDTO success(UserDTO user) {
        return new RegisterResponseDTO(user, null);
    }

    public static RegisterResponseDTO failure(List<String> errors) {
        return new RegisterResponseDTO(null, errors);
    }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}