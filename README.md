# SCA微服务脚手架

##### &nbsp;&nbsp;&nbsp;nacos: _http://10.66.66.214:8848/nacos/_ （注册中心）
##### sentinel: _http://10.66.66.214:8858/_ （服务治理）
##### &nbsp;&nbsp;&nbsp;zipkin: _http://10.66.66.214:9411/_ （服务追踪链路）
    com-yc-common
     - com-yc-common-base
     - com-yc-common-config
     - com-yc-common-util
    com-yc-gateway
    com-yc-authentication
     - com-yc-authentication-api
     - com-yc-authentication-core
    com-yc-mall
     - com-yc-mall-api
     - com-yc-mall-core
    com-yc-user
     - com-yc-user-api
     - com-yc-user-core
### com-yc-common(公共模块)
    对服务提供公共类库
### com-yc-gateway(网关)
    1.网关功能：上层路由(Nginx)所有/api/的请求以upstream负载均衡至此，通过path断言映射到对应nacos中注册的微服务(如: /user/ 路由到com-yc-user服务)。
    2.服务鉴权：对请求方IP、请求头请求来源字段、SIGN参数签名验证做统一校验处理。
    3.服务治理：集成sentinel，配置sentinle地址，配置流控规则数据来源为nacos配置中心，流控规则(dataId为${spring.application.name}-flow-rules)，降级规则(dataId为${spring.application.name}-degrde-rules
### com-yc-authentication(认证授权中心)
    统一负责微服务的认证授权，包含接口授权、微信openId授权、极光设备绑定、环信账号绑定等第三方账号绑定授权。
### com-yc-user(用户服务)
    提供用户信息相关服务，用户基本信息、用户角色、用户权限节点、用户权限节点组、用户菜单等
### com-yc-mall(商城服务)
    提供商城信息相关服务
### com-yc-sms(消息服务)
    阿里云短信发送服务，邮件发送服务，kafka生产消息服务