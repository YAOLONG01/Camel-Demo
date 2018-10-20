# Camel(04) 基于内容的路由CBR
## <font color=#5B96E6>简介</font>
```CBR（Content Based Router）```是一种消息路由器，它根据消息的内容将消息路由到目的地。内容可以是消息头、有效荷载数据类型、有效荷载本身的一部分等几乎是消息交换中的任何内容。

![image.png-28.3kB][1]

CBR根据消息内容路由消息，在这种情况下，文件扩展名（作为消息头）用于确定要路由到哪个对列。

## <font color=#5B96E6>使用方法：</font>
 - ```endwith```关键字过滤“以...”结尾
 - ```regex```关键字启用正则表达式
 - ```otherwise```关键字代表除此以外的其他情况
```java
// Java DSL
from("jms:incomingOrders")
.choice()
    .when(header("CamelFileName").endsWith(".xml"))
        .to("jms:xmlOrders")
    .when(header("CamelFileName").regex("^.*(csv|csl)$"))
        .to("jms:csvOrders")
    .otherwise()
        .to("jms:badOrders");
```

## <font color=#5B96E6>CBR之后的路由</font>
CBR可能看起来像是路由的终点，在cbr中消息被路由到几种情况中的一个，如果想让路由继续意味着需要另一条线路。

有两种方法可以在cbr之后继续路由。
1. 使用另一条线路。
2. 关闭选择块，并添加另一个目的地。
```java
// Java DSL
from("jms:incomingOrders")
.choice()
    .when(header("CamelFileName").endsWith(".xml"))
        .to("jms:xmlOrders")
    .when(header("CamelFileName").regex("^.*(csv|csl)$"))
        .to("jms:csvOrders")
    .otherwise()
        .to("jms:badOrders")
.end()
.to("jms:continuedProcessing");
```
使用end关闭选块，通过选择后的消息将被继续路由到end后的目的地。

如果想让某个消息经过选择后停下，而不是继续路由，可以使用stop方法：
```java
// Java DSL
from("jms:incomingOrders")
.choice()
    .when(header("CamelFileName").endsWith(".xml"))
        .to("jms:xmlOrders")
    .when(header("CamelFileName").regex("^.*(csv|csl)$"))
        .to("jms:csvOrders")
    .otherwise()
        .to("jms:badOrders").stop()
.end()
.to("jms:continuedProcessing");
```
以上代码在Spring DSL中如下：
```xml
<route>
	<from uri="jms:incomingOrders"/>
	<choice>
		<when>
			<simple>${header.CamelFileName} regex '^.*xml$'</simple>
			<to uri="jms:xmlOrders"/>
		</when>
		<when>
			<simple>${header.CamelFileName} regex '^.*(csv|csl)$'</simple>
			<to uri="jms:csvOrders"/>
		</when>
		<otherwise>
			<to uri="jms:badOrders"/>
			<stop/>
		</otherwise>
	</choice>
	<to uri="jms:continuedProcessing"/>
</route>
```

## <font color=#5B96E6>Recipient List接收者列表</font>
上面部分，展示了怎么使用条件判断向若干条可能的路由路径中的某一条路径传送消息。除此之外，还有如下两种需求：
 - 根据判断条件向若干可能的路径中的其中多条路径传送同一消息。
 - 向若干条可能的路径全部传输同一条消息。

这里我们需要解释一个概念，即接收者。接收者就是在Camel中可能被选择的消息路由路径。Camel提供了多种方式向路由中可能成为下一处理元素的多个接收者发送消息：
 - 静态接收者列表（Static Recipient List）
 - 动态接收者列表（Dynamic Recipient List）
 - 循环动态路由（Dynamic Router）

## <font color=#5B96E6>Multicast（组播路由）处理静态接收者列表</font>
使用Multicast方式时，Camel将会把上一处理元素输出的Exchange复制多分发送给这个列表中的所有接收者，并且按顺序逐一执行（可设置为并行处理）这些接收者。这些接收者可能是通过Direct连接的另一个路由，也可能是Processor或者某个单一的Endpoint。（注意：Exchange在复制时，主要的属性内容相同，但是使用的内存区域以及ExchangeID都不相同。

  



[1]: http://static.zybuluo.com/Wang-1997/wxcptu28cjvn52b108u98fnf/image.png 