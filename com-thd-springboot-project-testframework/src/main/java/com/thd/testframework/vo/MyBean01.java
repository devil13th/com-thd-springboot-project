package com.thd.testframework.vo;

/**
 * com.thd.springboot.edu.edu03.MyBean01
 *
 * @author: wanglei62
 * @DATE: 2021/4/25 11:47
 **/
public class MyBean01 {
    private MyBean02 myBean02;
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyBean02 getMyBean02() {
        return myBean02;
    }

    public void setMyBean02(MyBean02 myBean02) {
        this.myBean02 = myBean02;
    }
}
