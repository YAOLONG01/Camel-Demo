/**
 * Project Name: spring-boot-camel-rest
 * File Name: HttpProcess
 * Package Name: com.why.dev.springbootcamelrest
 * Date: 2018/11/24 11:46 PM
 * Copyright (c) 2018, Wang, Haoyue All Rights Reserved.
 */
package com.why.dev.springbootcamelrest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.json.simple.JsonObject;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: HttpProcess
 * Description: TODO ADD REASON
 * Date: 2018/11/24 11:46 PM
 *
 * @author Wang, Haoyue
 * @version V1.0
 * @since JDK 1.8
 */
@Component
public class HttpProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Map map = new HashMap();
        map.put("employeeId", "7f384d4b5f0f46209a99de6652eb92f7");
        map.put("employeeName", "测试3");
        JsonObject jsonObject = new JsonObject(map);
        System.out.println(jsonObject);
//        String body = "{\"employeeName\"=\"camel\", \"employeeId\"=\"7f384d4b5f0f46209a99de6652eb92f7\"}";
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setBody(jsonObject.toJson());
//        InputStream bodyStream = (InputStream) exchange.getIn().getBody();
//        String body = IOUtils.toString(bodyStream, "UTF-8");
//        System.out.println("Processor: " + body);
    }
}
