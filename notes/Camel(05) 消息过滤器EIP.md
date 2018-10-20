# Camel(05) 消息过滤器EIP
消息过滤器EIP，提供了一种对于消息的过滤功能。如果满足特定条件，使其通过；如果不满足，消息将被删除。

## <font color=#5B96E6>使用</font>
Java DSL
```java
from("jms:xmlOrders").filter(xpath("/order[not(@test)]"))
.process(new Processor() {
	public void process(Exchange exchange) throws Exception {
		System.out.println("Received XML order: "
				+ exchange.getIn().getHeader("camelFileName"));
	}
});
```
Spring DSL
```xml
<route>
    <from uri="jms:xmlOrders"/>
    <filter>
        <xpath>/order[not(@test)]</xpath>
        <process ref="orderLogger"/>
    </filter>
</route>
```

![image.png-13.8kB][1]

Tips:可以使用此功能过滤掉测试消息。


[1]: http://static.zybuluo.com/Wang-1997/az2zi4y94me7cmo6wqu4csix/image.png