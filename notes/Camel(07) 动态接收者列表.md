# Camel(07) 动态收件人列表

## <font color=#5B96E6>动态收件人列表</font>
首先检查传入的消息，然后根据内容生成列表，并将消息发送给列表中的收件人。
```java
from("jms:xmlOrders")
.recipientList(header("recipient"));
```
消息中包含信息可行的值是：
 - java.util.Collection
 - java.util.Iterator
 - Java数组
 - org.w3c.dom.NodeList
 - 逗号隔开的String

Java DSL
```java
from("jms:xmlOrders")
.setHeader("customer", xpath("/order/@customer"))
.process(new Processor() {
	public void process(Exchange exchange) throws Exception {
		String recipients = "jms:accounting";
		String customer = exchange.getIn().getHeader("customer", String.class);
		if (customer.equals("honda")) {
			recipients += ",jms:production";
		}
		exchange.getIn.setHeader("recipients", recipients);
	}
})
.recipients(header("recipients"));
```
Spring DSL
```xml
<route>
	<from uri="jms:xmlOrders"/>
	<setHeader headerName="customer">
		<xpath>/order/@customer</xpath>
	</setHeader>
	<process ref="calculateRecipients"/>
	<recipientList>
		<header>recipients</header>
	</recipientList>
</route>
```




