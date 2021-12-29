## 后端开发

### 数据库

**MySQL**（使用 [**MyBatis-Plus** ](https://baomidou.com/) 进行数据库CRUD操作、存储用户数据、表格数据）

1. 用户数据 - **user**
    - id（学号、主键） - **user_id**
    - 昵称 - **nickname**
    - 头像（URL类型） - **avatar**
    - 性别 - **gender**
    - 邮箱 - **email**
    - 电话号码 - **phone_number**
    - 密码（SHA256加密） - **password**
    - 最后登录时间 - **last_login_time**
2. 用户权限 - user_authority
    - id（权限ID） - **id**
    - 用户ID - **user_id**
    - 权限 - **role**
3. 用户发布的项目 - pr_user_project
    - 项目id - **project_id**
    - 项目所属用户 - **user_id**
4. 表单项类型 -
    - it_key( Item Type Key )
    - 类型名称（）
...
以json存储表格模板

- 表项有类型

---

**Redis**（存储Spring Session）

- 表项结构的json数据 - <project key，数据json>
- 常见表项 - 学号 - 手机号 - 身份证号
- 用户个人信息（实现自动填写） - **user_info** -  Hash
    - key: user_info
    - value: 用户已填写过信息的json数据

