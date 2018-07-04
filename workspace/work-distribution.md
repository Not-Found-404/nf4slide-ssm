# 工作分工
## 王海涛 (Billows Tao)
+ 个人中心界面
    + 幻灯片标题的显示
    + 基本的增删查改
    + 文件夹功能
        + 文件夹的添加，删除，改名
        + 同一个目录下既有幻灯片文件，也有文件夹
        + 幻灯片可以被移入或者移除某个文件夹
    + 搜索栏所示幻灯片，如英雄卡片教程那般
## 任小康 (ToableRen)
+ Spring事物配置
    + slide service(所有方法)
    + user service(所有方法)
    + 操作记录
        + 哪个用户 userid
        + ip地址 ipv4
        + 日期（date）
        + 操作（调用的哪个service方法）    
        + 参数（service）
+ 完善系统的健壮性
    + 所有的service方法
+ 写注释
    + 给所有的controller方法写注释
    + 给所有的service方法写注释
+ 给每个源代码文件添加LICESN
+ 整合mybatis和spring
    + SqlsessionFactory注入
## 葛化亚 (Arcry)
+ 注册界面
    + 至少三个项
        + 手机号 phoneNum
        + 用户名 username
        + 密码   password
        + 参数名必须和上面的一致
    + 不符合要求的项需要在输入框后面提示错误信息
    + 验证手机号是否已经被使用
        + 参数名 phoneNum
        + 返回结果都是json格式，如
            + `{'result':'success'','code':'200'}`
            + 后续的结果结构都如此的，不再赘述
    + 点击发送手机验证码，并要求用户填写
        + 发送验证码按钮在被点击后内60s内禁用
        + 禁用60s要有倒计时
    + 点击注册之后
        + 异步提交注册信息，后台会发回响应信息
        + 成功则直接跳转登陆，不成功则提示错误信息    
+ 登陆界面
+ 修改头像界面
## 梅茹 (Mary)
## 丁星 (wildhunt_unique)
