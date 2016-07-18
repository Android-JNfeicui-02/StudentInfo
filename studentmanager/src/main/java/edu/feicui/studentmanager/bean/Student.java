package edu.feicui.studentmanager.bean;

/**
 * 最基本的创建JavaBean的方式 getter/setter， 2个Constructor  toString
 * Created by admin on 2016/7/18.
 */
public class Student {
    private String name;
    private String stuNumber;
    private String phone;

    // Constructor
    public Student() {
    }

    public Student(String stuNumber,String name,  String phone) {
        this.stuNumber = stuNumber;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
