netsharp中对session的设计，主要是用户身份确认的设计

对于session的处理主要分成两种场景
1.web服务器的session
   要跟serverlet的的session保持一致，并考虑分布式缓存
2.rpc和数据库处理时候的session
  不维护session的状态，每次rpc的请求都把web的session信息传递到rpc的服务端

对于这两种的session的处理都是平台封装的，对于开发者而言，他们使用的方法是一样的，如下所示：
Object userId = SessionManager.getUserId();
Object departmentId = SessionManager.getDepartmentId();

netsharp封装了实现的细节，那么这些细节是什么呢？
