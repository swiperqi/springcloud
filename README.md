# springcloud
springcloud学习

---

需要先启动eureka，启动后可以访问localhost:8001进入eureka的监控面板，查看有哪些服务已经注册进eureka，被调用的服务必须已经注册进eureka

provider
---
springcloud-provider

服务提供者

feign
---
springcloud-consumer

>ex: localhost:8003/consumer/hello/qq  ->  localhsot:8002/provider/hello/qq

>ex: localhost:8003/consumer/world因为provider没有world接口，所以会触发熔断

服务提供者使用spring security的时候，调用的时候需要携带token，可以实现`RequestInterceptor`
接口，把请求头中的token携带过去

restTemplate调用
---

springcloud-consumer-ribbon

直接通过restTemplate调用其他服务，没有熔断

>ex: localhost:8004/ribbon/hello/qq  -> localhost:8002/provider/hello/qq

>ex: localhost:8004/ribbon/helloFeign/qq  ->  localhost:8003/consumer/hello/qq

负载均衡
---

更改端口号启动多个provider，通过feign和ribbon访问provider会轮询启动的provider

zuul
---

springcloud-zuul

通过配置zuul路由网关，可以直接访问zuul，然后由zuul转发到相应的服务

>ex: localhost:8010/feign/consumer/hello/qq -> localhost:8003/consumer/hello/qq

>ex: localhost:8010/provider/provider/hello/qq -> localhost:8002/provider/hello/qq

过滤器

可以设置有哪些请求可以不经过过滤直接进行路由，可以设置过滤规则，比如请求头里需要带有accessToken等，不符合的会被拦截不进行路由

jenkins部署
---
[jenkins.md](https://github.com/swiperqi/springcloud/blob/master/jenkins.md)

spring security oauth2
---

在pom文件添加
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-oauth2</artifactId>
    </dependency>
```
认证服务器需要继承`AuthorizationServerConfigurerAdapter`类，
如：`public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter`

资源服务器需要继承`ResourceServerConfigurerAdapter`类，
如：`public class ResourceServerConfig extends ResourceServerConfigurerAdapter`

需要到授权服务器获取access_token，再携带相应的access_token才能访问资源服务器的接口

使用密码获取token：
`localhost:8008/oauth/token?client_id=provider&client_secret=provider&grant_type=password&username=provider&password=provider`

携带token访问provider的接口：
`http://localhost:8002/provider/hello/qq?access_token=983f99c8-8a83-41eb-94ed-b7eaa26725b4`
或者在请求头中携带token
`http://localhost:8002/provider/hello/qq`
请求头：`Authorization`:`Bearer 983f99c8-8a83-41eb-94ed-b7eaa26725b4`

ps:如果使用system获取的token访问provider的接口会返回权限不足无法访问的情况（
`localhost:8008/oauth/token?client_id=system&client_secret=system&grant_type=password&username=system&password=system`）

pps:/oauth/token接口默认是使用post访问的，如果要使用get访问需要在资源服务器配置中重载
`configure(AuthorizationServerEndpointsConfigurer endpoints)`方法，配置
`endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)`

---
---

把不同服务部署到不同的服务器上时，如果服务器之间的内网ip不能互相调通，需要在配置文件中设置eureka.instance.ip-address为部署服务器的公网ip

maven：父工程pom文件中如果配置了`spring-boot-maven-plugin`，子工程中如果没有main方法（如springcloud-common），
不能被打包成jar包（如果子工程中的pom文件包含`<packaging>jar<packaging>`，那么在pom install的时候会报错），
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```