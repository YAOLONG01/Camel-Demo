# Camel(09) 数据转换-使用EIPs和Java

## <font color=#5B96E6>常见的6中数据转换方式</font>
 - 路由中的数据转换
 - 使用组件进行数据转换
 - Camel转换器
 - 模板
 - Camel的类型转换机制
 - 组件适配器中的消息转换

## <font color=#5B96E6>EIP转换器</font>
Camel提供三种使用EIP转换器的方式：
 - Processor
 - beans
 - transform

## <font color=#5B96E6>Exchange中的getIn和getOut方法</font>
Camel消息定义了两个方法：
 - getIn
 - getOut   

getIn方法返回传入的消息，getOut方法访问出站消息。
使用getOut时需要注意一点，传入消息的消息头和附件将会丢失，这通常不是想要的，因此必须将消息头和附件从传入消息复制到传出消息。另一种选择是使用getIn直接在传入消息上设置更改，而完全不使用getOut。

  


