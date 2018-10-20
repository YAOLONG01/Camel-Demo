# Camel(02) 路由
Camel最重要的特征之一就是路由，没有它Camel本质上将是一个传输连接器库。
在企业消息系统中，路由是从输入队列中获取消息，并根据一组条件将消息发送到多个输出队列之一的过程。

![image_1cpm73ugusb7ikqiav15anjvv9.png-13.5kB][1]   

在Apache Camel中，路由被定义为信息一步一步移动的过程，它起源于作为消费者角色的端点，使用者可以从外部服务器接收消息，在某个系统上轮询消息甚至创建消息本身。然后此消息将通过一个处理组件、处理器、拦截器等传递。消息最终被发送到作为生产者的目标端点。

Camel易于使用的一个特性是端点URI，通过指定URI，可以确定要使用的组件以及该组件是如何被配置的。然后可以决定将消息发送到由该URI配置的组件，或者使用该组件发出消息。

## <font color=#5B96E6>示例</font>
### <font color=#C8896F>FTP模块</font>
 - 轮询FTP服务器并下载新订单
 - 将订单文件转换为JMS消息
 - 将消息发送到JMS incomingOrders队列

### <font color=#C8896F>从FTP服务器下载新订单，需要执行以下操作：</font>
 - 连接到默认FTP端口为21的FTP服务器
 - 提供用户名“rider”和密码“secret”
 - 将目录更改为“orders”
 - 下载任何新订单文件

对于FTP组件，可以在URI的上下文路径中指定用户名和密码，因此如下两个URI具有相同意义。
```http
ftp://rider:secret@rider.com/orders
ftp://rider.com/orders?username=rider&password=secret
```
可以使用URI符号轻松地配置Camel来实现，Camel首先将在组件注册表中查找FTP方案，它将解析为FtpComponent。然后，FtpComponent作为工厂工作，根据上下文路径和选项创建FtpEndpoingt。

FtpComponent不是camel-core模块的一部分，因此您必须向项目添加一个addi依赖项。使用Maven，您只需向POM添加以下依赖项即可:
```xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-ftp</artifactId>
    <version>2.22.1</version>
</dependency>
```
在Camel DSL的from节点使用它：
```java
from("ftp://rider.com/orders?username=rider&password=secret")
```
### <font color=#C8896F>Camel endpoint的URI由三部分组成：</font>
![image_1cpm97jknbi51i341hgv671r9gm.png-6.1kB][2]
 - 方案
 - 上下文路径
 - 选项列表

## <font color=#5B96E6>Camel ActiveMQ</font>
对于Apache ActiveMQ，可以创建ActiveMQConnectionFactory指向正在运行的ActiveMQ代理：
```java
ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
```
这意味着您应该连接到当前JVM中运行的名为“localhost”的嵌入式代理。ActiveMQ中的vm传输连接器在尚未运行的情况下根据需要创建代理，因为它对于快速测试JMS应用程序非常方便；对于生产场景，建议您连接到已经运行的代理。此外，在生产场景中，我们建议在连接到JMS代理时使用连接池。

接下来，在创建CamelContext时，可以添加JMS组件:
```java
CamelContext context = new DefaultCamelContext();
context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
```
添加Maven依赖:
```xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jms</artifactId>
    <version>2.22.1</version>
</dependency>
```
```xml
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-core</artifactId>
    <version>5.7.0</version>
</dependency>
```

## <font color=#5B96E6>使用RouteBuilder</font>
### <font color=#C8896F>第一种方式：</font>
```java
class MyRouteBuilder extends RouteBuilder {
    public void configure() throws Exception
    {
        ...
    }
}
CamelContext context = new DefaultCamelContext();
context.addRoutes(new MyRouteBuilder());
```
### <font color=#C8896F>第二种方式：</font>
```java
CamelContext context = new DefaultCamelContext();
context.addRoutes(new RouteBuilder() {
    public void configure() throws Exception
    {
        ...
    }
});
```
下图所示的路由形成了一个简单的管道，FTP使用者的输出被输入到JMS生产者的输入中。从文件到JMS消息的有效负载转换是自动完成的。

![image_1cpoqkjd4f1q6hnnhk1ece1a7d9.png-17.7kB][3]

## <font color=#5B96E6>Endpoint</font>
### <font color=#C8896F>Endpoint Direct</font>
direct用于在两个编排好的路由间实现Exchange消息的连接，上一个路由中由最后一个元素处理完的Exchange对象，将被发送至Direct连接的下一个路由的起始位置。（注意：两个被连接的路由一定要是可用的，并且存在于同一个Camel服务中。）
direct元素在我们实际使用Camel进行路由编排时，应用频度非常高。因为它可以作为发起Camel路由的起始点，也可以把多个已经编排好的路由按照业务要求连接起来，形成一个新的路由，保持原有路由的良好重用。

## <font color=#5B96E6>Processor</font>
Camel的Processor接口是复杂路由的重要组成部分。通过Processor可以完全访问消息交换，可以使用内容或者头消息做任何事。
### <font color=#C8896F>Peocessor的作用</font>
接收从Endpoint、路由选择条件、处理器的Exchange中传来的消息，并进行处理。
### <font color=#C8896F>Processor的工作</font>
 - 数据格式转换
 - 中间数据的临时存储   

总结：Processor处理器是在Camel编排好的路由中进行Exchange输入和输出信息交换的地方。
### <font color=#C8896F>特殊使用</font>
在Processor处理器中可以连接数据库。例如：在Processor中可以连接到一个独立的数据库中（或者缓存服务中），查找对应的路由信息，以便决定下一个路由路径。由于Camel支持与Spring框架的集成，所以在Processor中操作数据库的配置不会很复杂。
```java
from("ftp://rider.com/orders?username=rider&password=secret").
    process(new Processor() {
        public void process(Exchange exchange) throws Exception {
            System.out.println("We just download: " + 
                    exchange.getIn().getHeader("CamelFileName"));
        }
    }).
    to("jms:incomingOrders");
```

   


[1]: http://static.zybuluo.com/Wang-1997/h41dospwy4saic4sndkicobm/image_1cpm73ugusb7ikqiav15anjvv9.png
[2]: http://static.zybuluo.com/Wang-1997/usukqbhbzeuejekx6s8uq7ky/image_1cpm97jknbi51i341hgv671r9gm.png
[3]: http://static.zybuluo.com/Wang-1997/moovf1n3e3s5r2uqxagwzocb/image_1cpoqkjd4f1q6hnnhk1ece1a7d9.png