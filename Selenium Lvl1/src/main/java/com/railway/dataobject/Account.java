package com.railway.dataobject;

public enum Account {

    VALID_ACCOUNT("kingohong@gmail.com", "123456789"),
    INVALID_LENGTH_PASSWORD("kingohong@gmail.com", "123456"),
    UNACTIVED_ACCOUNT("123@gmail.com", "123456789"),
    BLANK_ACCOUNT("", "123456789"),
    BLANK_PASSWORD("kingohong@gmail.com", ""),;

    private final String username;
    private final String password;

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

