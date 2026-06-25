package com.ortegainmo.app.dto.login;

public record ChangePasswordDTO(
        String currentPassword,
        String newPassword
) {}
