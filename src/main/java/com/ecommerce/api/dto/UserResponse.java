package com.ecommerce.api.dto;

import com.ecommerce.api.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private User.Role role;
    private LocalDateTime createdAt;
}
