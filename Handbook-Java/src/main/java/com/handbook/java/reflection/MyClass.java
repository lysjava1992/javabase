package com.handbook.java.reflection;

import java.util.Arrays;
import java.util.List;

public class MyClass {


    protected List<String> stringList= Arrays.asList("a","b","c");

    private List<User> userList=Arrays.asList(new User("Tom",5),new User("Ti",8));
    public List<String> getStringList(){
        return this.stringList;
    }
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
    public List<User> getUserList() {
        return userList;
    }
}
