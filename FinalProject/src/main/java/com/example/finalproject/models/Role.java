package com.example.finalproject.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER, EMPLOYEE, ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }
}
