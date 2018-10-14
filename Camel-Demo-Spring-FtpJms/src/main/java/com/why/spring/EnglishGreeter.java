package com.why.spring;

/**
 * @Author :王皓月
 * @Date :2018/10/14 下午6:16
 * @Description :
 */

public class EnglishGreeter implements Greeter {
    public String sayHello() {
        return "Hello " + System.getProperty("user.name");
    }
}
