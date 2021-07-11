回顾上次课程内容：

如何构建一个微服务及服务注册**(有问题？)**

客户端如何调用服务端的API(写死，负载均衡[LoadBalanceClient, @LoadBlanced])

#### Feign 客户端的调用框架







------------------------------------------------------------------------------------------------------------------------------------------------------

git / svn 

git配置: 

account:    271314998@qq.com

password: zxvnmqt@.123



### **Spring Cloud Config**

#### 1、服务端配置

##### 1.1 引入依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>commons-logging</artifactId>
            <groupId>commons-logging</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

##### 1.2 配置项

```
spring:
  cloud:
    zookeeper:
      connect-string: 192.168.3.201:2181
    config:
      server:
        git:
          uri: https://gitee.com/gerry123/config-repos
          username: 271314998@qq.com
          password: zxvnmqt@.123
```

##### 1.3 启动类配置

```
@EnableConfigServer 启用服务配置
```

**测试说明:** 

/{label}/{name}-{profiles}.yml

/{name}-{profiles}.yml / dev/prod/test

name git的文件名称不包括后缀

profiles 环境

label 分支(branch)

#### 2、客户配置

##### 2.1 引入依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-client</artifactId>
</dependency>
```

##### 2.2 配置项

```
spring:
  application:
    name: bookProvider
  cloud:
    config:
      discovery:
        enabled: true
        service-id: config # 配置服务项目名称
```

##### 2.3 高可用测试说明



### Spring Cloud Bus 手动实现刷配置(不重启项目情况下)

##### 安装RabbitMQ

1、先在www.erlang.org/downloads下载erlang的源码
2、http://www.rabbitmq.com/download.html下载rabbitMQ
3、安装依赖 
   yum install ncurses-devel openssl

4、解压erlang的源码 

 tar xf otp_src_20.1.tar.gz

 cd otp_src_20.1

 ./configure --prefix=/usr/local/erlang210 --without-javac



 ./configure --prefix=/usr/local/erlang210 --with-ssl --enable-threads --enable-smp-support --enable-kernel-poll --enable-hipe --without-javac



https://www.cnblogs.com/wuhenzhidu/p/rabitmq.html

https://blog.csdn.net/MRA__S__/article/details/54617913



 make -j 4
 make install

erlang /usr/local/erlang210/bin

5、安装python
​	yum install python -y
​	安装simplejson
​	yum install xmlto -y
​       a、yum install python-simplejson -y（如果安装失败请使用b）

​       b、进入simplejson文件夹，运行命令：python setup.py 或者先进如python编程模式，输入 setup.py install 

6、安装rabbitMQ 
​     xz -d rabbitmq-server-generic-unix-3.7.7.tar.xz
​    tar xf rabbitmq-server-generic-unix-3.7.7.tar
​    mv rabbitmq_server-3.7.7 /usr/local/rabbitmq
​    vim /etc/profile 配置环境变量:

​       到文件的末尾添加:

​	export PATH=$PATH:/usr/local/erlang210/bin

​	export PATH=$PATH:/usr/local/rabbitmq/sbin

​      使用配置马上生效: source /etc/profile

​    查看已经开放的端口：
​    firewall-cmd --list-ports

​    开启端口
​    firewall-cmd --zone=public --add-port=5672/tcp --permanent

​    firewall-cmd --zone=public --add-port=15672/tcp --permanent

7、启用RabbitMQWeb管理插件
​     rabbitmq-plugins enable rabbitmq_management 

8、创建用户
​    【用户】 ./rabbitmqctl add_user rabbit rabbit

​    【操作授权】该命令使用户test具有/vhost1这个/中所有资源的配置、写、读权限以便管理其中的资源
​      ./rabbitmqctl set_permissions -p / test ".*" ".*" ".*"

​     【角色授权】

​       ./rabbitmqctl set_user_tags test administrator

9、启动rabbitmq   

​      后台启动 ./rabbitmq-server -detached      

​	

10、SpringBoot加入RabbitMQ配置
   a> 添加依赖amqp并配置
​	#rabbitmq
​	spring.rabbitmq.host=192.168.3.101
​	spring.rabbitmq.port=5672
​	spring.rabbitmq.username=guest
​	spring.rabbitmq.password=guest
​	

##### 配置端引入依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##### 配置端配置项

```
rabbitmq:
    host: 192.168.3.201
    port: 5672
    username: test
    password: test
management:
  endpoints:
    web:
      exposure:
        include: '*' # 开启所有配置url
```
在controller上启用读取刷新数据注解@RefreshScope

### Spring Cloud Bus 实现自动刷配置(不重启项目情况下)+WebHooks

加入依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-monitor</artifactId>
</dependency>
```

想办法把自己机器变为服务器

在gitee里面配置webhook





[https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%2Fconfigure--prefix%3D%2Fusr%2Flocal%2Ferlang--with-ssl--enable-threads--enable-smp-support--enable-kernel-poll--enable-hipe--without-javac%20%20make%20%26%26%20install&rsv_pq=97b7c7c900026836&rsv_t=d893s3eOuZoGQWAKHsyKtHMSIKL8ERZJuTpys5vIYEt68d4aE975xaB2pg8&rqlang=cn&rsv_enter=0&rsv_dl=tb&rsv_n=2&rsv_sug3=1&inputT=1699&rsv_sug4=1700](https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%2Fconfigure--prefix%3D%2Fusr%2Flocal%2Ferlang--with-ssl--enable-threads--enable-smp-support--enable-kernel-poll--enable-hipe--without-javac  make %26%26 install&rsv_pq=97b7c7c900026836&rsv_t=d893s3eOuZoGQWAKHsyKtHMSIKL8ERZJuTpys5vIYEt68d4aE975xaB2pg8&rqlang=cn&rsv_enter=0&rsv_dl=tb&rsv_n=2&rsv_sug3=1&inputT=1699&rsv_sug4=1700)