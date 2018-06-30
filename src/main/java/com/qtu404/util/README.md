# qtu404开发工具包

1. ## email
    + 需要springcontext插件和javax
    
2. ## sms
    + `<dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-java-sdk-core</artifactId>
                   <version>3.2.8</version>
               </dependency>`
    + `<dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
                   <version>1.1.0</version>
               </dependency>`
3. ## web
    + ### dto
        
    + ### smm
        + ssm对应的controller层、service层、dao层
        + dao层
            + 需要继承`BaseDaoImpl<T>`，同时实现自己实体的dao接口
            + 需要重写`String getNamespaces()`方法， 返回实现类对应的namespace
            + 需要重写`SqlSessionFactory getSqlSessionFactory()`方法,得到对应SqlSessionFactory
            + mapper.xml 需要约定几个固定的操作id
                + 默认的删除id为'delete'
                + 默认的修改id为'modify'
                + 默认的添加id为'save'
                + 默认的查找所有id为'findAll'
                + 默认的通过id查找id为'fetchById'
                
        + service层
            + 需要继承`BaseServiceImpl<T>`，同时实现自己实体的service接口   
            + 需要重写`BaseDao<T> getBaseDao()`, 得到对应的BaseDao 
            
        + controller
            + 默认返回json字符串
            + 每个方法的映射地址与dao层的默认id相同    
    + ### result
        + result 结果
        + code  状态码
    