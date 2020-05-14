# springcloud
springcloud学习

---

需要先启动eureka，启动后可以访问localhost:8001进入eureka的监控面板，查看有哪些服务已经注册进eureka，被调用的服务必须已经注册进eureka

### provider
springcloud-provider

服务提供者

### feign
springcloud-consumer

>ex: localhost:8003/consumer/hello/qq  ->  localhsot:8002/provider/hello/qq

>ex: localhost:8003/consumer/world因为provider没有world接口，所以会触发熔断
### restTemplate调用

springcloud-consumer-ribbon

直接通过restTemplate调用其他服务，没有熔断

>ex: localhost:8004/ribbon/hello/qq  -> localhost:8002/provider/hello/qq

>ex: localhost:8004/ribbon/helloFeign/qq  ->  localhost:8003/consumer/hello/qq

### 负载均衡

更改端口号启动多个provider，通过feign和ribbon访问provider会轮询启动的provider

### zuul

通过配置zuul路由网关，可以直接访问zuul，然后由zuul转发到相应的服务

>ex: localhost:8010/feign/consumer/hello/qq -> localhost:8003/consumer/hello/qq

>ex: localhost:8010/provider/provider/hello/qq -> localhost:8002/provider/hello/qq

### jenkins部署
[jenkins.md](https://github.com/swiperqi/springcloud/blob/master/jenkins.md)

---
ps: 把不同服务部署到不同的服务器上时，如果服务器之间的内网ip不能互相调通，需要在配置文件中设置eureka.instance.ip-address为部署服务器的公网ip
