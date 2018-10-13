package com.why.filecopier.camelimpl;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author :王皓月
 * @Date :2018/10/13 下午3:23
 * @Description : Camel实现文件的拷贝
 */

public class FileCopierWithCamel {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // noop=true: 表示此操作为复制，如果去掉此参数则为移动
                from("file:data/inbox?noop=true")
//                from("file:data/inbox")
                        .to("file:data/outbox");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
