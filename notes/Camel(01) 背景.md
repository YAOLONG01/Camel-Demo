# Camel(01) 背景
Apache Camel是一个功能强大的开源集成框架，基于已知的企业集成模式和强大的bean集成。Camel项目于2007年初启动，Camel的重点是简化集成。Camel框架的核心是路由引擎，或者更确切地说是一个路由引擎建造者。它允许自定义路由规则，从中决定来源接受消息并确定如何处理和发送这些消息到其他目的地。

Camel的原则之一是它没有做出任何假设你需要处理的数据类型，这是一个重点，因为开发人员有机会集成任何类型的系统，从而无需将数据转换为规范格式。

## <font color=#5B96E6>介绍</font>
Camel允许您创建EIP（Enterpise Integration Patterns, 企业集成模式），以基于Java的DSL（Domain Specific Language, 域特定语言）或Fluent API，通过Spring或Blueprint的xml配置文件或者通过Scala DSL来实现路由和中介规则。这意味着无论是在Java、Scala或者是XML编辑器中都可以在IDE中使用自动提示从而智能地完成路由规则。

Apache Camel使用URIs，因此它可以借助可插入的数据格式选项轻松的与任何类型的传输或消息传递模型（如HTTP、ActiveMQ、JMS、JBI、SCA、MINA或CXF）一起使用。Apache Camel是一个小型的库，具有最小的依赖性，可以轻松嵌入任何Java应用程序。无论使用何种传输方式，Apache Camel都使用相同的API，因此只需要学习一次API即可与开箱即用的所有组件进行交互。

Apache Camel具有强大的Bean绑定功能，可与Spring、CDI、Blueprint和Guice等流行框架无缝集成。

Apache Camel拥有广泛的测试支持，可以轻松的对路由进行单元测试。

## <font color=#5B96E6>组件</font>
Apache Camel附带了许多组件，数据格式和语言等。

## <font color=#5B96E6>DSL</font>
以下示例为路由文件从文件夹到JMS对列。
 - Java DSL
```
from("file:data/inbox").to("jms:queue:order");
```
 - Spring DSL
```
<route>
    <from uri="file:data/inbox"/>
    <to uri="jms:queue:order"/>
</route>
```
Camel DSL使用Java等真正的编程语言，因此您可以使用现有的工具支持。在这里，您可以看到Eclipse IDE的自动完成功能如何为我们提供DSL列表:

![image.png-202.5kB][1]

## <font color=#5B96E6>Camel消息模型</font>
在Camel中有两种消息模型的抽象。
 - org.apache.camel.Message
 - org.apache.camel.Exchange
### <font color=#C8896F>Message</font>
Message是系统在使用时彼此通信的实体消息渠道。Message从发送者到接收者以一个方向流动。

![image.png-12.1kB][2]  

Message有一个Body、headers和可选的attachments。

![image.png-18.4kB][3]
### <font color=#C8896F>Exchange</font>
Camel中的Exchange是路由期间消息的容器，Exchange也为系统之间的各种类型的交互提供支持，也成为消息交换模式MEP。  
 - ExchangeID   
一个Exchange贯穿着整个编排的路由规则，ExchangeID就是它的唯一编号信息，同一个路由规则的不同实例（对路由规则分别独立的两次执行），ExchangeID不相同。
 - from Endpoint    
表示exchange实例初始来源的Endpoint控制端点（类的实例），一般来说就是开发人员设置路由时由“from”关键字所表达的Endpoint。
 - properties   
Exchange对象贯穿整个路由执行过程中的控制端点、处理器甚至还有表达式、路由条件判断。为了让这些元素能够共享一些开发人员自定义的参数配置信息，Exchange以K-V结构提供了这样的参数配置信息存储方式。在org.apache.camel.impl.DefaultExchange类中，对应properties的实现代码如下所示：    
```java
public Map<String, Object> getProperties() {
    if (properties == null) {
        properties = new ConcurrentHashMap<String, Object>();
    }
    return properties;
}
```
 - Pattern  
Exchange中的pattern属性非常重要，它的全称是：ExchangePattern（交换器工作模式）。其实现是一个枚举类型：org.apache.camel.ExchangePattern。可以使用的值包括：InOnly, RobustInOnly, InOut, InOptionalOut, OutOnly, RobustOutOnly, OutIn, OutOptionalIn。从Camel官方已公布的文档来看，这个属性描述了Exchange中消息的传播方式。

![image.png-49kB][4]    

![image_1cq3mtjec1a1819eg150hsnb4h39.png-29kB][5]
### Exchange中的Message
Exchange中还有两个重要属性inMessage和outMessage。这两个属性分别代表Exchange在某个处理元素（处理器、表达式等）上的输入消息和输出消息。

当控制端点和处理器、处理器和处理器间的Message在Exchange中传递时Exchange会自动将上一个元素的输出值作为作为这个元素的输入值进行使用。但是如果在上一个处理器中，开发人员没有在Exchange中设置任何out message内容（即Excahnge中out属性为null），那么上一个处理器中的in message内容将作为这个处理器的in message内容。

## <font color=#5B96E6>Camel架构</font>
![image.png-123.3kB][6]
### <font color=#C8896F>CamelContext</font>
![image.png-37.1kB][7]
### <font color=#C8896F>Routes</font>
 - 动态决定客户端将调用哪个服务器
 - 提供灵活的方式来添加额外的处理
 - 允许独立开发客户端和服务器
 - 允许将服务器的客户端存根（使用模拟）进行测试
 - 增强某些系统的功能和特性（例如消息代理和ESBs）
 
    


  [1]: http://static.zybuluo.com/Wang-1997/t5wo7ts3j9s5bubrxi2y1jr8/image.png
  [2]: http://static.zybuluo.com/Wang-1997/xew12ymk9nzqsfhgpfe0y55e/image.png
  [3]: http://static.zybuluo.com/Wang-1997/n5430zhsb3dlpy0w5jh2k5ss/image.png
  [4]: http://static.zybuluo.com/Wang-1997/5pl2cbp287e0tn0ctqmvl5gq/image.png
  [5]: http://static.zybuluo.com/Wang-1997/wth3tppr3nronf3ub1hv8hs8/image_1cq3mtjec1a1819eg150hsnb4h39.png
  [6]: http://static.zybuluo.com/Wang-1997/d3lp4u9qxsnxl8snh25yjrsj/image.png
  [7]: http://static.zybuluo.com/Wang-1997/nevc2t9idu0wrzeaz4jhp6y4/image.png