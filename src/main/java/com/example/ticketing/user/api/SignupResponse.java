package com.example.ticketing.user.api;

import com.example.ticketing.user.domain.User;
import com.example.ticketing.user.domain.UserRole;

public record SignupResponse
    (Long id,
    String email,
    UserRole role)
{
    public static SignupResponse from(User user){
        return new SignupResponse(user.getId(), user.getEmail(), user.getRole());
    }
}
