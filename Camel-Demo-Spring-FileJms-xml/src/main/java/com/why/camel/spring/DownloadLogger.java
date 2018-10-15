package com.why.camel.spring;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by 1594215 on 10/15/2018.
 */
public class DownloadLogger implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just download: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
