package com.gogent.impl;

import com.gogent.annotation.Example;
import com.gogent.domain.Employee;
import com.gogent.interfaces.AbstractExampleExecutable;
import java.io.*;

@Example(command = "ser", ref = SerializationDemo.class, description = "演示序列化和反序列化操作")
public class SerializationDemo extends AbstractExampleExecutable {
    public void process() {
        Employee employeeO = new Employee("Gogent", 28, "G10086", "jkfjkasdnvkio23infw");
        System.out.printf("序列化前的对象：\n%s\n", employeeO.toString());
        byte[] bin = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(employeeO);
            bin = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bin == null) {
            System.out.println("Serialization fail");
        } else {
            System.out.printf("序列化后的对象的字节（翻译成String）：\n%s\n", new String(bin));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bin);
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                Employee employee1 = (Employee) ois.readObject();
                System.out.printf("反序列化的对象：\n%s\n", employee1.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
