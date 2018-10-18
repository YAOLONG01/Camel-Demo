package com.why.camel.http;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;


/**
 * @Author :王皓月
 * @Date :2018/10/16 下午9:35
 * @Description :
 */

@Component
public class HttpProcess implements Processor {
    public void process(Exchange exchange) throws Exception {
        InputStream bodyStream = (InputStream) exchange.getIn().getBody();
        String body = IOUtils.toString(bodyStream, "UTF-8");
        System.out.println("Processor: " + body);
    }
}
