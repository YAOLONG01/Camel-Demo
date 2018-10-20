# Camel(08) wireTap辅助目标

## <font color=#5B96E6>wireTap辅助目标</font>
wireTap是一种非常有用的监控工具。如果想对传入的消息进行审核、检查等操作，使用wireTap可以将消息的副本发送到辅助目标，而不会影响路由其余部分的行为。wireTap有一个固定的收件人列表，发送消息的副本到目的地。

![image.png-14.5kB][1]

Java DSL
```java
from("jms:incomingOrders")
.wireTap("jms:orderAudit")
	.choice()
		.when(header("CamelFileName").endsWith(".xml"))
			.to("jms:xmlOrders")
		.when(header("CamelFileName").regex("^.*(csv|csl)$"))
			.to("jms:csvOrders")
		.otherwise()
			.to("jms:badOrders");
```
上面的代码将消息的副本发送到一个jms队列，并且不影响原始的消息继续路由。Camel不会等待wireTap的响应，因为它的交换模式为InOnly。
Spring DSL
```xml
<route>
    <from uri="jms:incomingOrders"/>
    <wireTap uri="jms:orderAudit"/>
    ...
</route>
```

## <font color=#5B96E6>使用场景</font>
 - 使用wireTap将消息打印在控制台，有利于调试
 - 将消息保存在持久性存储中（文件或数据库），以便以后检索





[1]: http://static.zybuluo.com/Wang-1997/0jklavelepn5ewn8nnyxst1i/image.png




