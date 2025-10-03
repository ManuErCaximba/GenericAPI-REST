package com.generic.ecommerce.main.service.interfaces;

import com.generic.ecommerce.main.dto.request.RegisterRequestDTO;
import com.generic.ecommerce.main.dto.response.RegisterResponseDTO;

public interface IAuthService {
    RegisterResponseDTO register(RegisterRequestDTO request);
}
