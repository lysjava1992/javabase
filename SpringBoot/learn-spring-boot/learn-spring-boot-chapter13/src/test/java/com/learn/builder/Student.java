package com.learn.builder;

import lombok.Data;

@Data
public class Student {
    private String name;
    private int age;
    private String email;
    private String addr;

    public Student(StudentBuilder studentBuilder) {
        this.name = studentBuilder.name;
        this.age = studentBuilder.age;
        this.email = studentBuilder.email;
        this.addr = studentBuilder.addr;
    }
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder{
        private String name;
        private int age;
        private String email;
        private String addr;
        private StudentBuilder name(String name){
            this.name=name;
            return this;
        }
        private StudentBuilder age(int age){
            this.age=age;
            return this;
        }
        private StudentBuilder email(String email){
            this.email=email;
            return this;
        }
        private StudentBuilder addr(String addr){
            this.addr=addr;
            return this;
        }
        private Student builder(){
            return new Student(this);
        }
    }

    public static void main(String[] args) {
        Student student=Student.builder()
                               .age(18)
                               .name("Tom")
                               .builder();
    }
}
