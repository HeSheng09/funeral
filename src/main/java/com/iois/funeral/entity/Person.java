package com.iois.funeral.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: hesheng
 * @create: 2020-12-2020/12/30
 * @description:
 **/
public class Person {

    @JSONField(name = "name")
    private String name;
    @JSONField(name = "gender")
    private int gender;
    @JSONField(name = "age")
    private int age;
    @JSONField(name = "birth")
    private String birth;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "marriage_status")
    private String marriage_status;
    @JSONField(name = "Education")
    private String education;
    @JSONField(name = "mingzu")
    private String mingzu;
    @JSONField(name = "chushengdi")
    private String chushengdi;
    @JSONField(name = "jiguan")
    private String jiguan;
    @JSONField(name = "zhengzhimianmao")
    private String zhengzhimianmao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarriage_status() {
        return marriage_status;
    }

    public void setMarriage_status(String marriage_status) {
        this.marriage_status = marriage_status;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMingzu() {
        return mingzu;
    }

    public void setMingzu(String mingzu) {
        this.mingzu = mingzu;
    }

    public String getChushengdi() {
        return chushengdi;
    }

    public void setChushengdi(String chushengdi) {
        this.chushengdi = chushengdi;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    public String getZhengzhimianmao() {
        return zhengzhimianmao;
    }

    public void setZhengzhimianmao(String zhengzhimianmao) {
        this.zhengzhimianmao = zhengzhimianmao;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                ", address='" + address + '\'' +
                ", marriage_status='" + marriage_status + '\'' +
                ", education='" + education + '\'' +
                ", mingzu='" + mingzu + '\'' +
                ", chushengdi='" + chushengdi + '\'' +
                ", jiguan='" + jiguan + '\'' +
                ", zhengzhimianmao='" + zhengzhimianmao + '\'' +
                '}';
    }
}
