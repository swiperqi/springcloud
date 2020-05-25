# jenkins部署
* 在jenkins创建一个新项目，选择freestyle project
* 创建完之后进入配置界面
* 在Source Code Management中选择Git
* 填写Repositories -> Repository URL为github/gitlab项目地址（需要保证服务器可以从github/gitlab拷贝），
在Credentials选择一个用户，没有的话需要添加
* 在Build Environment勾选Delete workspace before build starts
* 在Build中点击Add build step，选择Invoke top-level Maven targets，点击Advanced，
在Goals中输入clean install -Dmaven.skip.test=true，
POM中输入pom.xml（github项目首页中的pom.xml，如果要只构建一个的话，比如eureka的，可以填写springcloud-eureka/pom.xml，
可以添加多个Invoke top-level Maven targets）
* 在Build中点击Add build step，选择Execute Shell，在command中填写脚本，比如：
```shell script
# 部署在两台服务器上（一台腾讯云node62，一台阿里云node47，jenkins在node62），centos7
# workspace是jenkins打包后存放jar包的路径，'springcloud'是jenkins项目名
workspace=/var/lib/jenkins/workspace/springcloud

mv ${workspace}/springcloud-eureka/target/springcloud-eureka-1.0-SNAPSHOT.jar ~/springcloud/eureka/
mv ${workspace}/springcloud-consumer-ribbon/target/springcloud-consumer-ribbon-1.0-SNAPSHOT.jar ~/springcloud/ribbon/
mv ${workspace}/springcloud-consumer/target/springcloud-consumer-1.0-SNAPSHOT.jar ~/springcloud/consumer/
# node47为另一台服务器的ip(47.***.***.***)，如果只用一台服务器的话，下面两句可以改成和上面类似的
scp ${workspace}/springcloud-provider/target/springcloud-provider-1.0-SNAPSHOT.jar root@node47:~/springcloud/provider/
scp ${workspace}/springcloud-zuul/target/springcloud-zuul-1.0-SNAPSHOT.jar root@node47:~/springcloud/zuul/
scp ${workspace}/springcloud-system/target/springcloud-system-1.0-SNAPSHOT.jar root@node47:~/springcloud/system/
# 执行启动脚本
sh ~/springcloud/restart-47.sh
# 执行另一台服务器的启动脚本
# 要设置两台服务器ssh免密连接
ssh node47 "sh ~/springcloud/restart-62.sh"
```
restart-47.sh:
```shell script
# 把之前运行的程序杀掉
kill -9 $(ps -ef | grep java | grep 8001 | awk '{print $2}')
#kill -9 $(ps -ef | grep java | grep 8002 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8003 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8004 | awk '{print $2}')
# 没有这一句，jenkins跑完后会把启动的java程序再杀掉
export BUILD_ID=dontKillMe
# node62为本台服务器的ip(62.***.***.***)
# 如果只在一台服务器，可以把另外的服务添加在这里
nohup java -jar ~/springcloud/eureka/springcloud-eureka-1.0-SNAPSHOT.jar --clientIp=node62 --server.port=8001 > ~/springcloud/eureka/info.log &
#nohup java -jar ~/springcloud/provider/springcloud-provider-1.0-SNAPSHOT.jar --server.port=8002 > ~/springcloud/provider/info.log &
nohup java -jar ~/springcloud/consumer/springcloud-consumer-1.0-SNAPSHOT.jar --clientIp=node62 --server.port=8003 > ~/springcloud/consumer/info.log &
nohup java -jar ~/springcloud/ribbon/springcloud-consumer-ribbon-1.0-SNAPSHOT.jar --clientIp=node62 --server.port=8004 > ~/springcloud/ribbon/info.log &

sleep 10
```
restart-62.sh:
```shell script
kill -9 $(ps -ef | grep java | grep 8002 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8005 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8006 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8008 | awk '{print $2}')
kill -9 $(ps -ef | grep java | grep 8010 | awk '{print $2}')

export BUILD_ID=dontKillMe
# 启动3个provider
# node62是eureka服务所在的服务器的ip(62.***.***.***)
nohup java -jar ~/springcloud/provider/springcloud-provider-1.0-SNAPSHOT.jar --clientIp=node47 --server.port=8002 --eurekaHost=node62 --dataSourceUrl=node62 > ~/springcloud/provider/info8002.log &
nohup java -jar ~/springcloud/provider/springcloud-provider-1.0-SNAPSHOT.jar --clientIp=node47 --server.port=8005 --eurekaHost=node62 --dataSourceUrl=node62 > ~/springcloud/provider/info8005.log &
nohup java -jar ~/springcloud/provider/springcloud-provider-1.0-SNAPSHOT.jar --clientIp=node47 --server.port=8006 --eurekaHost=node62 --dataSourceUrl=node62 > ~/springcloud/provider/info8006.log &
nohup java -jar ~/springcloud/system/springcloud-system-1.0-SNAPSHOT.jar --clientIp=node47 --server.port=8008 --eurekaHost=node62 --dataSourceUrl=node62 > ~/springcloud/system/info.log &
# node47为本服务器ip(47.***.***.***)
nohup java -jar ~/springcloud/zuul/springcloud-zuul-1.0-SNAPSHOT.jar --clientIp=node47 --server.port=8010 --eurekaHost=node62 > ~/springcloud/zuul/info.log &

sleep 10
```
* 配置完之后点击save，回到构建页面，点击左侧的build now开始构建，左下角会显示构建进度条，点击闪烁的球可以查看构建日志，
球变成蓝色为构建成功，红色为构建失败

