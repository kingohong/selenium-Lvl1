package com.railway.dataobject;

public enum ChangePassword {

    VALID_CHANGE_PASSWORD("123456789", "123456789", "123456789", null),
    CHANGE_PASSWORD_WITHOUT_TOKEN(null, "123456789", "123456789", ""),
    DIFFERENT_CONFIRM_PASSWORD(null, "123456789", "123456", ""),;

    private final String currentPassword;
    private final String newPassword;
    private final String confirmPassword;
    private final String token;

    ChangePassword(String currentPassword, String newPassword, String confirmPassword, String token) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.token = token;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getToken() {
        return token;
    }
}

