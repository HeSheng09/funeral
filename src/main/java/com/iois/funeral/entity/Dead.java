package com.iois.funeral.entity;

public class Dead {
    private  String uid;
    private int stage;

    @Override
    public String toString() {
        return "Dead{" +
                "uid='" + uid + '\'' +
                ", stage=" + stage +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public Dead(String uid, int stage) {
        this.uid = uid;
        this.stage = stage;
    }

    public boolean validation(){
        if("000000000000000000".equals(this.uid)||this.uid.length()!=18){
            return false;
        }else return this.stage >= 0 && this.stage <= 5;
    }
}
