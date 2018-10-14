package com.why.camel.spring;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author :王皓月
 * @Date :2018/10/14 下午6:57
 * @Description :
 */

public class FtpToJMS extends RouteBuilder {
    public void configure() throws Exception {
        from("ftp://rider.com" +
                "/orders?username=rider&password=secret")
                .to("jms:incomingOrders");
    }
}
