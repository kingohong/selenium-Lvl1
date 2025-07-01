package com.railway.dataobject;

public enum Account {

    VALID_ACCOUNT("kingohong@gmail.com", "123456789", null, null),
    INVALID_LENGTH_PASSWORD("kingohong@gmail.com", "123456", null, null),
    UNACTIVED_ACCOUNT("123@gmail.com", "123456789", null, null),
    BLANK_ACCOUNT("", "123456789", null, null),
    BLANK_PASSWORD("kingohong@gmail.com", "", null, null),

    VALID_REGISTER_ACCOUNT("", "123456789", "123456789", "123456789"),
    BLANK_EXCEPT_EMAIL("","","",""),
    DIFFERENT_PASSWORD("","123456789","123456","123456789");

    private String username;
    private final String password;
    private final String confirmPassword;
    private final String pid;

    Account(String username, String password, String confirmPassword, String pid) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.pid = pid;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPid() {
        return pid;
    }
}

