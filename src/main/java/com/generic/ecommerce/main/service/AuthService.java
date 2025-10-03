package com.generic.ecommerce.main.service;

import com.generic.ecommerce.main.dto.model.UserDTO;
import com.generic.ecommerce.main.dto.request.RegisterRequestDTO;
import com.generic.ecommerce.main.dto.response.RegisterResponseDTO;
import com.generic.ecommerce.main.model.User;
import com.generic.ecommerce.main.repository.UserRepository;
import com.generic.ecommerce.main.service.interfaces.IAuthService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        UserDTO dto = request.getUser();
        // basic normalizations
        String email = dto.getEmail() != null ? dto.getEmail().trim().toLowerCase() : null;

        // validations and conflicts
        if (email != null && userRepository.existsByEmail(email)) {
            return RegisterResponseDTO.failure(java.util.List.of("El email ya está registrado"));
        }

        User user = new User();
        user.setFirstName(dto.getFirstName().trim());
        user.setLastName(dto.getLastName().trim());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        try {
            User saved = userRepository.save(user);
            UserDTO out = new UserDTO();
            out.setId(saved.getId());
            out.setFirstName(saved.getFirstName());
            out.setLastName(saved.getLastName());
            out.setEmail(saved.getEmail());
            // never return password
            out.setPassword(null);
            return RegisterResponseDTO.success(out);
        } catch (DataIntegrityViolationException ex) {
            return RegisterResponseDTO.failure(java.util.List.of("Usuario o email ya existen"));
        }
    }
}
