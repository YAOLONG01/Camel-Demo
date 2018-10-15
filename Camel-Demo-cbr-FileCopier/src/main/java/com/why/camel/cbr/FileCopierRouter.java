package com.why.camel.cbr;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


/**
 * Created by 1594215 on 10/15/2018.
 */
public class FileCopierRouter {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:src/data?noop=true")
                        .choice()
                        .when(header("CamelFileName")
                                .endsWith(".txt"))
                        .to("file:src/data/out/txt")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("Received TXT: "
                                        + exchange.getIn().getHeader("CamelFileName"));
                            }
                        })
                        .when(header("CamelFileName")
                                .endsWith(".xml"))
                        .to("file:src/data/out/xml")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("Received XML: "
                                        + exchange.getIn().getHeader("CamelFileName"));
                            }
                        });
            }
        });

        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
