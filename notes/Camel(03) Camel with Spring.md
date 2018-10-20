# Camel(03) Camel whit Spring
## <font color=#5B96E6>Maven依赖</font>
```xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-core</artifactId>
    <version>2.22.1</version>
</dependency>
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-spring</artifactId>
    <version>2.22.1</version>
</dependency>
```
使用Maven插件运行Camel，需要在pom.xml中添加：
```xml
<build>
    <plugins>
        <!-- Allows the example to be run via 'mvn compile exec:java' -->
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
                <configuration>
                    <includePluginDependencies>false</includePluginDependencies>
                </configuration>
        </plugin>

        <!-- Allows the routes to be run via 'mvn camel:run' -->
        <plugin>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## <font color=#5B96E6>Spring配置文件</font>
在sources/META-INF/spring下创建beans.xml配置文件:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://camel.apache.org/schema/spring
       http://camel.apache.org/schema/spring/camel-spring.xsd">
       
    <bean id="peocessor" class="com.why.camel.spring.Processor"/>

    <camelContext xmlns="http://camel.apache.org/schema/spring">
        <route>
            <from uri=""/>
            <to uri=""/>
        </route>
        <route>
            <from uri=""/>
            <process ref="peocessor"/>
        </route>
    </camelContext>

</beans>
```

## <font color=#5B96E6>运行</font>
基于xml的Camel工程有两种运行方法：
1. 添加camel-maven-plugin插件，执行```camel:run```运行。
```
mvn clean compile camel:run
```
2. 在main函数中扫描xml配置文件，并根据情况使用如下两种方式：
```java
Thread.sleep(10000);
```
```java
synchronized (App.class) {
    App.class.wait();
}
```

## <font color=#5B96E6>使用哪种DSL</font>
Java DSL拥有完整的功能，一些Java DSL功能，如值构建器等在Spring DSL中不可用。但是Spring DSL可以使用对象构造功能以及数据库连接等常用的Spring抽象和JMS集成。一个常见的折中方案是同时使用Spring DSL和Java DSL。

在Spring容器中运行Camel会带来许多其他好处。首先，如果使用Spring DSL，则在更改路由规则时不必重新编译任何代码。此外，还可以访问Spring的数据库连接器组合，事务支持等。

使用Spring CamelContext和Java DSL进行路由开发是使用那个Camel的好方法。可以明确告诉CamelContext使用的RouteBuilder。

## <font color=#5B96E6>配置文件分离</font>
### <font color=#C8896F>jms-setup.xml</font>
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://camel.apache.org/schema/spring
       http://camel.apache.org/schema/spring/camel-spring.xsd">
	   
	<bean id="jms" class="org.apache.camel.component.jms.JmsComponent">
		<property name="connectionFactory">
			<bean class="org.apache.activemq.ActiveMQConnectionFactory">
				<property name="brokerURL" value="vm://localhost"/>
			</bean>
		</property>
	</bean>

</beans>
```
### <font color=#C8896F>CamelContext.xml</font>
```xml
<import resource="jms-setup.xml"/>
```
### <font color=#C8896F>RouteContext.xml</font>
```xml
<routeContext id="ftpToJms" xmlns="http://camel.apache.org/schema/spring">
    <route>
        <from uri="ftp://rider.com/orders?username=rider&password=secret"/>
        <to uri="jms:incomingOrders"/>
    </route>
</routeContext>
```
### <font color=#C8896F>CamelContext.xml</font>
```xml
<camelContext xmlns="http://camel.apache.org/schema/spring">
    <routeContextRef ref="ftpToJms"/>
</camelContext>
```




