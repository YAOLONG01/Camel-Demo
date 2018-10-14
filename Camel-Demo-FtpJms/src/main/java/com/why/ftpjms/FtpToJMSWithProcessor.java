package com.why.ftpjms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

/**
 * @Author :王皓月
 * @Date :2018/10/14 下午3:57
 * @Description : 从FTP下载转为JMS消息并发送到JMS消息队列
 */

public class FtpToJMSWithProcessor {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("vm://localhost");
        context.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("ftp://rider.com/orders?username=rider&password=secret").
                        process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("We just download: " + exchange.getIn().getHeader("CamelFileName"));
                            }
                        }).
                        to("jms:incomingOrders");
            }
        });

        context.start();
        Thread.sleep(10000);

        context.stop();
    }
}
