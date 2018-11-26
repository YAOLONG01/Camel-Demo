/**
 * Project Name: Camel-Demo-SpringBoot-File
 * File Name: FileProcessor
 * Package Name: com.why.dev.camel
 * Date: 2018/11/26 10:47 PM
 * Copyright (c) 2018, Wang, Haoyue All Rights Reserved.
 */
package com.why.dev.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * ClassName: FileProcessor
 * Description: TODO ADD REASON
 * Date: 2018/11/26 10:47 PM
 *
 * @author Wang, Haoyue
 * @version V1.0
 * @since JDK 1.8
 */
@Component
public class FileProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String fileName = (String) exchange.getIn().getHeader("CamelFileName");
        System.out.println("Processor -> File Name: " + fileName);

        String fileContent = exchange.getIn().getBody(String.class);
        System.out.println("Processor -> File Content: " + fileContent);
        String upperCaseFileContent = fileContent.toUpperCase();
        exchange.getIn().setBody(upperCaseFileContent);
    }
}
