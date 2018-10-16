package com.camel.cxf.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2017-12-31T12:35:16.206+08:00
 * Generated source version: 3.1.7
 * 
 */
@WebService(targetNamespace = "http://server.cxf.camel.com/", name = "CamelCXFServiceInter")
public interface CamelCXFServiceInter {

    @WebMethod
    @RequestWrapper(localName = "queryInfomation", targetNamespace = "http://server.cxf.camel.com/", className = "com.camel.cxf.server.QueryInfomation")
    @ResponseWrapper(localName = "queryInfomationResponse", targetNamespace = "http://server.cxf.camel.com/", className = "com.camel.cxf.server.QueryInfomationResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String queryInfomation(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

	@WebMethod
	@RequestWrapper(localName = "sayHello", targetNamespace = "http://server.cxf.camel.com/", className = "com.camel.cxf.server.SayHello")
	@ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://server.cxf.camel.com/", className = "com.camel.cxf.server.SayHelloResponse")
	@WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
