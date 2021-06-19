package com.example.veles_app.Model;
//класс в котором хранятся логин и пароль
public class Users {

    private String user, password;

    public Users(){

    }
//принять и получить каждый параметр
    public Users(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
