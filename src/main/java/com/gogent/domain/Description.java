package com.gogent.domain;

public class Description {
    String command;

    Class<?> clazz;

    String discription;

    public Description(String command, Class<?> clazz, String discription) {
        this.command = command;
        this.clazz = clazz;
        this.discription = discription;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Description{" +
                "command='" + command + '\'' +
                ", clazz=" + clazz +
                ", discription='" + discription + '\'' +
                '}';
    }
}
