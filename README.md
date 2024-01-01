# Simple Trading system
Small demo of simple trading system based on Springboot.



## **主要模块**：

- 用户管理模块
- 股票信息模块
- 交易模块



## 数据库设计

数据库脚本包括一些股票样例数据已经写在了`Database/create.sql` 中

1. **users**:存储用户信息
   
   - **user_id**: 用户编号，数据库自动生成，主键
   
   - **username**：用户名，不可重复
   
   - **email**：用户邮箱，不可重复
   
   - **active**：是否启用，用于逻辑删除（禁用）用户
   
   - **update_at**：最后更新时间戳，数据库自动生成
   
   - **reserved_text**： 可变长度的字符串字段，用于存储任何额外的文本信息。
   
   - **reserved_number**：数值字段，用于存储任何其他数值。
   
     
   
2. **stocks**:存储股票信息
   
   - **stock_name**：股票名称，主键
   - **stock_symbol**：股票代码
   - **current_price**：当前价格
   - **pe_ratio**：市盈率
   - **volume**：交易量
   - **market_cap**：市值
   - **52_week_high**：52周最高价
   - **52_week_low**：52周最低价
   - **reserved_text**： 可变长度的字符串字段，用于存储任何额外的文本信息。
   - **reserved_number**：数值字段，用于存储任何其他数值。



3. **orders**:记录用户的交易意向，包括买入或卖出的股票、数量和价格。用于追踪订单状态。

   - **order_id**: 订单编号，数据库自动生成，主键。

   - **user_id**: 用户ID，外键，指向users表。

   - **stock_symbol**: 股票代码，外键，指向stocks表。

   - **order_type**: 订单类型，如 'BUY' 或 'SELL'。

   - **quantity**: 订单股票数量。

   - **price**: 订单价格。

   - **order_time**: 下单时间，数据库自动生成。

   - **status**: 订单状态，如 'OPEN', 'CLOSED', 'CANCELLED'。

     

4. **trade history**:记录实际完成的交易，每条记录代表一个完成的交易。用于提供用户的交易历史。

   - **trade_id**: 交易编号，数据库自动生成，主键。

   - **order_id**: 关联的订单编号，外键，指向orders表。

   - **user_id**: 用户ID，外键，指向users表。

   - **stock_symbol**: 股票代码，外键，指向stocks表。

   - **trade_type**: 交易类型，如 'BUY' 或 'SELL'。

   - **quantity**: 交易股票数量。

   - **price**: 交易价格。

   - **trade_time**: 交易时间，数据库自动生成。

     

5. **market_info**:存储与交易市场相关的信息

   1. **market_id**: 市场信息的唯一标识符，数据库自动生成，主键。
   2. **open_time**: 市场开市时间。
   3. **close_time**: 市场收市时间。
   4. **total_volume**: 当日市场总交易量。
   5. **reserved_text**: 可变长度的字符串字段，用于存储任何额外的文本信息。
   6. **reserved_number**: 数值字段，用于存储任何其他数值。

6. Roles：存储用户name以及对应的角色
   1. **roles**:用户角色（管理员，客户）
   2. **username**:用户name

### API设计

#### 1.用户功能

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



#### 3. 市场交易功能

##### - 交易市场信息获取（/api/market/info）

**接口URL**: GET *http://localhost:8080/api/market/info*

**描述**: 获取当前的交易市场信息，如开市时间、收市时间和总交易量。

**返回值**

```
jsonCopy code{
  "success": true,
  "message": "Market info retrieved successfully.",
  "data": {
    "openTime": "09:00",
    "closeTime": "16:00",
    "totalVolume": 100000
    // 更多市场信息字段...
  }
}
```



##### - 交易下单（/api/orders）

**Content-Type**: application/json

**接口URL**: POST *http://localhost:8080/api/orders*

**描述**: 用户下单购买或出售股票，需要提供用户ID、股票代码、交易类型（买入或卖出）、数量和价格。

**Body请求参数**

```json
jsonCopy code{
  "userId": 1,
  "stockSymbol": "AAPL",
  "orderType": "BUY",
  "quantity": 10,
  "price": 150.00
}
```

**返回值**

```json
jsonCopy code{
	"success": true,
	"message": "Order placed successfully.",
	"data": {
		"orderId": 1,
		"status": "OPEN"
		// 更多订单详情...
	}
}
```



##### - 查看交易历史（/api/tradeHistory/{userId}）

**接口URL**: GET *http://localhost:8080/api/tradeHistory/{userId}*

**描述**: 用户可以查看自己的交易历史记录。需要在URL中指定用户ID。

**返回值**

```json
jsonCopy code{
	"success": true,
	"message": "Trade history retrieved successfully.",
	"data": [
		{
			"tradeId": 1,
			"stockSymbol": "AAPL",
			"tradeType": "BUY",
			"quantity": 5,
			"price": 150.00,
			"tradeTime": "2023-01-01T12:00:00"
			// 更多交易详情...
		},
		// 更多交易记录...
	]
}
```
