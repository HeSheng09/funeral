package com.iois.funeral.entity;

public class User {
    private String uid;
    private String name;
    private String password;

    public User(String uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
    }

    private Dead dead;

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dead=" + dead +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dead getDead() {
        return dead;
    }

    public void setDead(Dead dead) {
        this.dead = dead;
    }

    public User(String uid, String name, String password, Dead dead) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.dead = dead;
    }

    public boolean validation(){
        if("000000000000000000".equals(this.uid) || this.uid.length()!=18){
            return false;
        }
        if("invalid_name".equals(this.name) || this.name.length()>32){
            return false;
        }
        if("invalid_password".equals(this.password)||this.password.length()>32){
            return false;
        }
        if(this.dead!=null){
            return this.dead.validation();
        }else return true;
    }
}
