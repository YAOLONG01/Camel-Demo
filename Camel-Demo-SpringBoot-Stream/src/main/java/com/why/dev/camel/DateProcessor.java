/**
 * Project Name: Camel-Demo-SpringBoot-Stream
 * File Name: DateProcessor
 * Package Name: com.why.dev.camel
 * Date: 2018/11/26 11:21 PM
 * Copyright (c) 2018, Wang, Haoyue All Rights Reserved.
 */
package com.why.dev.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateProcessor
 * Description: TODO ADD REASON
 * Date: 2018/11/26 11:21 PM
 *
 * @author Wang, Haoyue
 * @version V1.0
 * @since JDK 1.8
 */
@Component
public class DateProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = simpleDateFormat.format(date);
        exchange.getIn().setBody(formatDate);
    }
}
