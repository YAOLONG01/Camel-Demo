package com.why.spring;

/**
 * @Author :王皓月
 * @Date :2018/10/14 下午6:17
 * @Description :
 */

public class DanisGreeter implements Greeter {
    public String sayHello() {
        return "Davs " + System.getProperty("user.name");
    }
}
