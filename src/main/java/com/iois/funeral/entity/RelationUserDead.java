package com.iois.funeral.entity;

public class RelationUserDead {
    private int id;
    private String user_id;
    private String dead_id;

    @Override
    public String toString() {
        return "RelationUserDead{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", dead_id='" + dead_id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDead_id() {
        return dead_id;
    }

    public void setDead_id(String dead_id) {
        this.dead_id = dead_id;
    }

    public RelationUserDead(int id, String user_id, String dead_id) {
        this.id = id;
        this.user_id = user_id;
        this.dead_id = dead_id;
    }

    public RelationUserDead(String user_id, String dead_id) {
        this.user_id = user_id;
        this.dead_id = dead_id;
    }
}
