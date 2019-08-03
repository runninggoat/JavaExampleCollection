package com.gogent.domain;

import java.io.Serializable;

public class Employee implements Serializable {
    String name;
    Integer age;
    String jobNumber;
    transient String token;

    public Employee(String name, Integer age, String jobNumber, String token) {
        this.name = name;
        this.age = age;
        this.jobNumber = jobNumber;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", jobNumber='" + jobNumber + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
