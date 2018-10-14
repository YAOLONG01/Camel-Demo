package com.why.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author :王皓月
 * @Date :2018/10/14 下午6:18
 * @Description :
 */

public class GreetMeBean {
    private Greeter greeter;

    public Greeter getGreeter() {
        return greeter;
    }

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }

    public void excute() {
        System.out.println(greeter.sayHello());
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        GreetMeBean bean = (GreetMeBean) context.getBean("greetMeBean");
        bean.excute();
    }
}
