package com.why.camel.spring;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

/**
 * Created by 1594215 on 10/15/2018.
 */
public class DateProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody(new Date().toString());
    }
}
