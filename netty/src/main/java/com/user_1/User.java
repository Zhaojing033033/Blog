package com.user_1;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String name;
    private int age;
    private String addr;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public User(int id, String name, int age, String addr) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
}
