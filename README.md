# Simple Trading system
Small demo of simple trading system based on Springboot.



## **主要模块**：

- 用户管理模块
- 股票信息模块



## 数据库设计

数据库脚本包括一些股票样例数据已经写在了`Database/create.sql` 中

1. users
   - user_id: 用户编号，数据库自动生成，主键
   - username：用户名，不可重复
   - email：用户邮箱，不可重复
   - active：是否启用，用于逻辑删除（禁用）用户
   - update_at：最后更新时间戳，数据库自动生成
2. stocks
   - stock_name：股票名称，主键
   - stock_symbol：股票代码
   - current_price：当前价格
   - pe_ratio：市盈率
   - volume：交易量
   - market_cap：市值
   - 52_week_high：52周最高价
   - 52_week_low：52周最低价



### API设计

#### 1.用户api

##### - 用户登录（/users/login）

Content-Type：application/json

接口URL：POST  *http://localhost:8080/api/users/login*

Body请求参数

```json
{
  "username": "user1",
  "password": "password"
}
```

返回值

```json
{
	"success": true,
	"message": "Login successful."
}
```



##### - 用户注册（/users/register）

Content-Type：application/json

接口URL：POST  *http://localhost:8080/api/users/register*

Body请求参数

```json
{
  "username": "user4",
  "password": "password",
  "email": "user4@example.com"
}

```

返回值

```json
{
	"userId": 16,
	"username": "user4",
	"password": "$2a$10$wVBePwtsnyhWSfTRY3yNQufJML0O0/uZadvesIJzNThv8l192ze5C",
	"email": "user4@example.com",
	"updatedAt": null,
	"active": true
}
```



##### - 用户信息查询（/users/getByName/{username}）

Content-Type：None

接口URL：GET  *http://localhost:8080/api/getByName/{username}*

返回值

```json
{
	"success": true,
	"message": "Username: user4, Email: user4@example.com"
}
```



##### - 用户信息修改（/users/update/{username}）

Content-Type：application/json

接口URL：PUT  *http://localhost:8080/api/users/updata/{username}*

Body请求参数

```json
{
  "password": "password123",
  "email": "useruser@example.com"
}
```

返回值

```json
{
	"success": true,
	"message": "User updated successfully."
}
```



##### - 用户禁用（/users/delete/{username}）

Content-Type：None

接口URL：DELETE *http://localhost:8080/api/users/delete/{username}*

返回值

```json
{
	"success": true,
	"message": "User disabled successfully."
}
```



#### 2.股票信息查询



##### - 获取所有股票（/stocks）

Content-Type：None

接口URL：GET  *http://localhost:8080/api/stocks*

返回值

```json
[
	{
		"stockId": 1,
		"stockName": "Apple Inc.",
		"stockSymbol": "AAPL",
		"currentPrice": 150,
		"peRatio": 25,
		"volume": 1000000,
		"marketCap": 2000000000,
		"week52High": null,
		"week52Low": null,
		"updatedAt": "2023-12-18T20:35:51"
	},
    ....
]
```



##### - 获取特定股票（/stocks/{stock_id}）

Content-Type：None

接口URL：GET http://localhost:8080/api/stocks/1

返回值

```json
{
	"stockId": 1,
	"stockName": "Apple Inc.",
	"stockSymbol": "AAPL",
	"currentPrice": 150,
	"peRatio": 25,
	"volume": 1000000,
	"marketCap": 2000000000,
	"week52High": null,
	"week52Low": null,
	"updatedAt": "2023-12-18T20:35:51"
}
```
