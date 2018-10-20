# Camel(06) Multicast组播

## <font color=#5B96E6>Multicast（组播路由）</font>
通常在企业应用程序中，您需要将消息的副本发送到多个不同的目标以进行处理。如果提前知道目标列表它是静态的，则可以向路径添加元素，该元素将使用来自端点的消息，然后将消息发送dao到目标列表。

![image.png-21.4kB][1]

## <font color=#5B96E6>Multicast（组播路由）处理静态接收者列表</font>
使用Multicast方式时，Camel将会把上一处理元素输出的Exchange复制多分发送给这个列表中的所有接收者，并且按顺序逐一执行（可设置为并行处理）这些接收者。这些接收者可能是通过Direct连接的另一个路由，也可能是Processor或者某个单一的Endpoint。（注意：Exchange在复制时，主要的属性内容相同，但是使用的内存区域以及ExchangeID都不相同。

## <font color=#5B96E6>使用</font>
默认情况下，Multicast多播按顺序发送消息副本。   
Java DSL
```java
from("jms:xmlOrders")
.multicast()
.to("jms:accounting", "jms:production");
```

## <font color=#5B96E6>并行多播</font>
```java
from("jms:xmlOrders")
	.multicast().parallelProcessing()
	.to("jms:accounting", "jms:production");
```
这将设置Multicast以并行的方式将消息分发到目的地。如果未指定任何其他内容，则使用默认线程池大小为10。

可以使用下面的例子，设置底层的```java.util.concurrent.ExecutorService```通过启动新的异步消息发送方发。
```java
ExecutorService executor = Executors.newFixedThreadPool(16);

from("jms:xmlOrders")
	.multicast().parallelProcessing().executorService(executor)
	.to("jms:accounting", "jms:production");
```
在默认情况下，即使一个目标发生故障，多播也会继续向目的地发送消息。

## <font color=#5B96E6>异常停止</font>
启用异常停止后，此功能将在捕获到第一个异常时停止多播，如下所示：
Java DSL
```java
from("jms:xmlOrders")
	.multicast().stopOnException()
	.parallelProcessing().executorService(executor)
	.to("jms:accounting", "jms:production");
```
Spring DSL
```xml
<bean id="executor" class="java.util.concurrent.Executors" factory-method="newFixedThreadPool">
	<constructor-arg index="0" value="16"/>
</bean>

<route>
	<from uri="jms:xmlOrders"/>
	<multicast stopOnException="true" parallelProcessing="true" executorServiceRef="executor">
		<to uri="jms:accounting"/>
		<to uri="jms:production:/>
	</multicast>
</route>
```

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

## <font color=#5B96E6>wireTap辅助目标</font>
如果想对传入的消息进行审核、检查等操作，使用wireTap可以将消息的副本发送到辅助目标，而不会影响路由其余部分的行为。wireTap有一个固定的收件人列表，发送消息的副本到目的地。

![image.png-14.5kB][2]

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

 


[1]: http://static.zybuluo.com/Wang-1997/zcbd20gfkozt3o8xf27x0yyp/image.png
[2]: http://static.zybuluo.com/Wang-1997/0jklavelepn5ewn8nnyxst1i/image.png