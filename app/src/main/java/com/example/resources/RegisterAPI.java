package com.example.resources;

public class RegisterAPI {

    private String Name;
    private String UserName;
    private String Email;

    public String getName() {
        return Name;
    }

    public RegisterAPI(String name, String userName, String email, String password) {
        Name = name;
        UserName = userName;
        Email = email;
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Password;
}
