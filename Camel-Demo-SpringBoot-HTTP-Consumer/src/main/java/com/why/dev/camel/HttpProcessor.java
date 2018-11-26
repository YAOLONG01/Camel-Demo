/**
 * Project Name: Camel-Demo-SpringBoot-HTTP-Consumer
 * File Name: HttpProcessor
 * Package Name: com.why.dev.camel
 * Date: 2018/11/26 11:48 PM
 * Copyright (c) 2018, Wang, Haoyue All Rights Reserved.
 */
package com.why.dev.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.json.simple.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: HttpProcessor
 * Description: TODO ADD REASON
 * Date: 2018/11/26 11:48 PM
 *
 * @author Wang, Haoyue
 * @version V1.0
 * @since JDK 1.8
 */
@Component
public class HttpProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        JsonObject jsonObject = new JsonObject(map);
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setBody(jsonObject.toJson());
    }
}
