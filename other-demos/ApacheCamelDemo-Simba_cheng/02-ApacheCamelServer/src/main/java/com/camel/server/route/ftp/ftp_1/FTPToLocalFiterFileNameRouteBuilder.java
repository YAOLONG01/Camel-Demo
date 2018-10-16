package com.camel.server.route.ftp.ftp_1;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * 从FTP服务器上下载文件，并根据正则表达式进行文件名过滤。
 * 
 * @author CYX
 * @time 2017年12月19日下午8:15:06
 */
public class FTPToLocalFiterFileNameRouteBuilder extends RouteBuilder {

	// 从FTP上下载文件(文件后缀名过滤)
	@Override
	public void configure() throws Exception {
		from("ftp://10.0.227.66/?username=cloudsftp&password=cloudsftp&binary=true&delete=true&delay=5000&include=.*conf").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {

				// 读取文件名
				// GenericFileMessage message = (GenericFileMessage)
				// exchange.getIn();
				// System.out.println(message);

				// 读取文件中的内容
				// String str = exchange.getIn().getBody(String.class);
				// System.out.println(str);
			}
		}).to("file:/temp");
	}

}
