# 115官网API(二)

## 一.用户管理

### 查询（分页实现）

[GET] / userCenter / getUsers

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pageSize | Y        | 一页显示多少条 |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,					//总条数
				"users_list":[
						{
							"id":10000,							//用户id
							"name":"admin",					//用户名
							"password":“123456”,  	//用户密码
							“age”:18,								//年龄
							"gender":"女", 			 	 //性别
							“phone”:"13800138000",  //手机号
							"email":"123@qq.com",		//邮箱
							"create_time":"2019-07-26",	//用户创建时间
							"last_login":"2019-12-05",	//用户最后登录时间
							"status":0,									//权限，0为普通用户，1管理员，2超级管理员
							"remark":"用户备注",
							"head_image":"用户头像url",
						}
				]
		}
}
~~~



### 修改用户信息

[POST] / userCenter / modifyUser

参数

| 参数名称   | 是否必须 | 说明                                      |
| ---------- | -------- | ----------------------------------------- |
| name       | Y        | 用户名                                    |
| password   | Y        | 密码                                      |
| age        | N        | 年龄                                      |
| gender     | N        | 性别                                      |
| phone      | N        | 联系方式                                  |
| email      | N        | 邮箱                                      |
| status     | Y        | 用户权限[0普通用户，1管理员，2超级管理员] |
| remark     | N        | 账号备注                                  |
| head_image | N        | 用户头像                                  |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":null
}

{
		"code":1,
		"msg":"用户名格式错误",
		"data":null
}

{
		"code":2,
		"msg":"用户名格式错误",
		"data":null
}

{
		"code":2,
		"msg":"密码格式错误",
		"data":null
}

{
		"code":3,
		"msg":"年龄超出范围",
		"data":null
}

{
		"code":4,
		"msg":"手机号码格式错误",
		"data":null
}

{
		"code":5,
		"msg":"邮箱格式错误",
		"data":null
}
~~~



###删除用户信息

[POST] / userCenter / delUser

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| id       | Y        | 要删除的用户id |

返回

~~~ 
{
		"code":0,
		"msg":"删除成功",
		"data":null
}
~~~



### 增加用户

[POST] / userCenter / addUser

参数

| 参数名称   | 是否必须 | 说明                                      |
| ---------- | -------- | ----------------------------------------- |
| name       | Y        | 用户名                                    |
| password   | Y        | 密码                                      |
| age        | N        | 年龄                                      |
| gender     | N        | 性别                                      |
| phone      | N        | 联系方式                                  |
| email      | N        | 邮箱                                      |
| status     | Y        | 用户权限[0普通用户，1管理员，2超级管理员] |
| remark     | N        | 账号备注                                  |
| head_image | N        | 用户头像                                  |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":null
}

{
		"code":1,
		"msg":"用户名格式错误",
		"data":null
}

{
		"code":2,
		"msg":"用户名格式错误",
		"data":null
}

{
		"code":2,
		"msg":"密码格式错误",
		"data":null
}

{
		"code":3,
		"msg":"年龄超出范围",
		"data":null
}

{
		"code":4,
		"msg":"手机号码格式错误",
		"data":null
}

{
		"code":5,
		"msg":"邮箱格式错误",
		"data":null
}
~~~



## 二.项目管理

### 查询（分页）

[GET] / projectCenter / getProjects

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pageSize | Y        | 一页显示多少条 |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,				//总条数
				"projects_list":[
						{
							"id":1,										//项目id
							"description":"活动",  		 //项目描述
							“time”:"2019-01-01",			//活动时间
							“image”:{
									"compress":,					//缩略图url
									"raw":,								//原图url
							}
						}
				]
		}
}
~~~



### 修改

[POST] / projectCenter / modifyProject

参数

参数

| 参数名称    | 是否必须 | 说明           |
| ----------- | -------- | -------------- |
| id          | Y        | 要修改的项目id |
| description | Y        | 项目描述       |
| time        | Y        | 项目时间       |
| image       | Y        | 项目图片       |

返回

 ~~~ 
{
		"code":0,
		"msg":"success",
		"data":null
}

{
		"code":1,
		"msg":"项目描述不能为空",
		"data":null
}

{
		"code":2,
		"msg":"项目图片不能为空",
		"data":null
}
 ~~~



### 删除

[POST] / projectCenter / delProject

参数

| 参数名称 | 是否必须 | 说明         |
| -------- | -------- | ------------ |
| id       | Y        | 删除项目的id |

返回

~~~ 
{
		"code":0,
		"msg":"删除成功",
		"data":null
}
~~~



### 增加

[POST] / projectCenter / addProject

参数

| 参数名称    | 是否必须 | 说明     |
| ----------- | -------- | -------- |
| description | Y        | 项目描述 |
| time        | N        | 项目时间 |
| image       | Y        | 项目图片 |

返回

~~~
{
		"code":0,
		"msg":"success",
		"data":null
}

{
		"code":1,
		"msg":"项目描述不能为空",
		"data":null
}

{
		"code":2,
		"msg":"项目图片不能为空",
		"data":null
}
~~~



## 三.寄语管理

### 查询(分页)

[GET] / wishCenter / getWishes

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pageSize | Y        | 一页显示多少条 |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				"total":15,					//总条数
				"wishes_list":[
						{
							"id":1,					//寄语id
							"name":"张三",	 //姓名
							"word":"祝福",	 //寄语内容
							"status":1,			//寄语状态，0审核不通过，1待审核，2审核通过待发布，3已发布
						}
				]
		}
}
~~~



### 修改

[POST] / wishCenter / modifyWish

参数

| 参数名称    | 是否必须 | 说明     |
| ----------- | -------- | -------- |
| description | Y        | 项目描述 |

返回

~~~ 
{
		"code":0,
		"msg":"修改成功",
		"data":null
}
~~~



### 删除

[POST] / wishCenter / delWish

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| id       | Y        | 要删除的寄语id |

返回

~~~ 
{
		"code":0,
		"msg":"删除成功",
		"data":null
}
~~~



## 四.动态管理

###查询（分页）

[GET] / dynamicCenter / getDynamics

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pageSize | Y        | 一页显示多少条 |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,			//总条数
				"dynamics_list":[
						{
							"id":1,							//动态编号
							"image":,						//图片URL
							"header":"标题",
							"text":"内容",
							"time":"动态发布时间",
							"editor":"发布者",
							"catagory":1,				//分类编号待定
						}
				]
		}
}
~~~



### 修改

[POST] / dynamicCenter / modifyDynamic

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| id       | Y        | 要修改的动态id |
| image    | Y        | 项目图片       |
| header   | Y        | 标题           |
| text     | Y        | 内容           |
| catagory | N        | 分类编号       |

返回

~~~
{
		"data":0,
		"msg":"success",
		"data":null
}

{
		"data":1,
		"msg":"动态标题不能为空",
		"data":null
}

{
		"data":2,
		"msg":"动态内容不能为空",
		"data":null
}
~~~



### 删除

[POST] / dynamicCenter / delDynamic

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| id       | Y        | 要删除的动态id |

返回

~~~ 
{
		"code":0,
		"msg":"删除成功",
		"data":null
}
~~~



### 增加

[POST] / dynamicCenter / addDynamic

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| image    | N        | 动态图片 |
| header   | Y        | 项目时间 |
| text     | Y        | 项目图片 |
| editor   | N        | 发布者   |
| catagory | N        | 分类编号 |

返回

~~~
{
		"code":0,
		"msg":"success",
		"data":null
}

{
		"code":1,
		"msg":"动态标题不能为空",
		"data":null
}

{
		"code":2,
		"msg":"动态内容不能为空",
		"data":null
}
~~~



## 五.其他功能

后续加入