# 115官网API(二)

## 一.用户管理

### 查询（分页实现）

[GET] / userCenter / getUsers

参数

| 参数名称 | 是否必须 | 说明                                       |
| -------- | -------- | ------------------------------------------ |
| page     | Y        | 页数                                       |
| pageSize | Y        | 一页显示多少条                             |
| status   | Y        | 0普通用户，1管理员，2超级管理员，3查询全部 |

正则表达式验证

~~~ 
//用户名,正则   ^[\u4e00-\u9fff\wa-zA-Z0-9_-]{5,13}$   描述：5到13位(汉子，字母，数字，下划线，减号)
//密码，正则   ^(\w){6,20}$  描述：6到20位(字母，数字，下划线)
//学号，正则		^[0-9]{8}$ 描述：8位数字
//年龄，正则		^(?:[1-9][0-9]?|1[01][0-9]|120)$ 描述：1到120岁
//手机号，正则	^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$
//邮箱，正则		^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$
~~~



返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,					//总条数
				"users_list":[
						{
							"id":10000,							
							"username":"admin",					//用户名
							"password":“123456”,  	//用户密码
							"student_id":"学号"			//学号
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



### 通过id查询

[POST]/userCenter/getUserById

参数

id 必须

返回


~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": 30,
        "username": "admin",
        "password": "admin",
        "student_id": null,
        "age": null,
        "gender": null,
        "phone": null,
        "email": null,
        "create_time": "2019-12-14 07:28:20",
        "last_login": null,
        "status": 2,
        "remark": null,
        "head_image": "http://localhost:8082/null"
    }
}
~~~



### 通过用户名查询

[POST]/userCenter/getUserByUsername

参数

name 必须

返回


~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": 30,
        "username": "admin",
        "password": "admin",
        "student_id": null,
        "age": null,
        "gender": null,
        "phone": null,
        "email": null,
        "create_time": "2019-12-14 07:28:20",
        "last_login": null,
        "status": 2,
        "remark": null,
        "head_image": "http://localhost:8082/null"
    }
}
~~~







### 修改用户信息

[POST] / userCenter / modifyUser

参数

| 参数名称   | 是否必须 | 说明                                      |
| ---------- | -------- | ----------------------------------------- |
| id         | Y        | 用户id                                    |
| name       | Y        | 用户名                                    |
| password   | Y        | 密码                                      |
| student_id | N        | 学号                                      |
| age        | N        | 年龄                                      |
| gender     | N        | 性别                                      |
| phone      | N        | 联系方式                                  |
| email      | N        | 邮箱                                      |
| status     | Y        | 用户权限[0普通用户，1管理员，2超级管理员] |
| remark     | N        | 账号备注                                  |
| head_image | N        | 用户头像URL                               |



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
		"msg":"密码格式错误",
		"data":null
	}
	
	{
		"code":3,
		"msg":"学号格式错误",
		"data":null
	}
	
	{
		"code":4,
		"msg":"年龄超出范围",					//整数范围0-120
		"data":null
	}

	{
		"code":5,
		"msg":"手机号码格式错误",
		"data":null
	}

	{
		"code":6,
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
		"msg":"success",
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
| student_id | N        | 学号                                      |
| age        | N        | 年龄                                      |
| gender     | N        | 性别                                      |
| phone      | N        | 联系方式                                  |
| email      | N        | 邮箱                                      |
| status     | Y        | 用户权限[0普通用户，1管理员，2超级管理员] |
| remark     | N        | 账号备注                                  |
| head_image | N        | 用户头像URL                               |

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
		"msg":"密码格式错误",
		"data":null
	}
	
	{
		"code":3,
		"msg":"学号格式错误",
		"data":null
	}
	
	{
		"code":4,
		"msg":"年龄超出范围",					//整数范围0-120
		"data":null
	}

	{
		"code":5,
		"msg":"手机号码格式错误",
		"data":null
	}

	{
		"code":6,
		"msg":"邮箱格式错误",
		"data":null
	}
	
	{
		"code":7,
		"msg":"图片大小超出限制"		//限制10MB
		"data":null
	}

~~~



## 二.项目管理

### 查询（分页）

[POST] / projectCenter / getProjects

参数

| 参数名称 | 是否必须 | 说明                  |
| -------- | -------- | --------------------- |
| page     | Y        | 页数                  |
| pageSize | Y        | 一页显示多少条(5~100) |

返回

~~~ 
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,				//总条数
				"projects_list":[
						{
							"id":1,										//id
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

| 参数名称         | 是否必须 | 说明                            |
| ---------------- | -------- | ------------------------------- |
| id               | Y        | 要修改的项目id                  |
| description      | Y        | 项目描述                        |
| time             | Y        | 项目时间                        |
| rawImageURL      | Y        | 项目原图片URL（相对服务器链接） |
| compressImageURL | Y        | 项目压缩图URL（相对服务器链接） |

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
		"msg":"项目时间不能为空",
		"data":null
	}
	
	{
		"code":3,
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
		"msg":"success",
		"data":null
}
~~~



### 增加

[POST] / projectCenter / addProject

参数

| 参数名称         | 是否必须 | 说明          |
| ---------------- | -------- | ------------- |
| description      | Y        | 项目描述      |
| time             | N        | 项目时间      |
| rawImageURL      | Y        | 项目原图URL   |
| compressImageURL | Y        | 项目压缩图URL |

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
		"msg":"项目时间不能为空",
		"data":null
	}
	
	{
		"code":3,
		"msg":"项目图片不能为空",
		"data":null
	}

~~~



## 三.寄语管理

### 查询(分页)

[GET] / wishCenter / getAllWishes

参数

| 参数名称 | 是否必须 | 说明                                                         |
| -------- | -------- | ------------------------------------------------------------ |
| page     | Y        | 页数                                                         |
| pageSize | Y        | 一页显示多少条                                               |
| status   | Y        | 0审核不通过，1待审核，2审核通过待发布，3发布，4查询全部状态寄语 |

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



### 修改寄语状态

[POST] / wishCenter / modifyWish

参数

| 参数名称 | 是否必须 | 说明             |
| -------- | -------- | ---------------- |
| status   | Y        | 寄语状态 0 1 2 3 |
| id       | Y        | 寄语id           |

返回

~~~ 
{
		"code":0,
		"msg":"success",
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
		"msg":"success",
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

| 参数名称 | 是否必须 | 说明                          |
| -------- | -------- | ----------------------------- |
| id       | Y        | 要修改的动态id                |
| image    | N        | 动态原图URL（相对服务器链接） |
| header   | Y        | 动态标题                      |
| text     | Y        | 动态内容                      |
| time     | N        | 动态时间                      |
| editor   | N        | 发布者                        |
| category | N        | 分类编号                      |



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
		"msg":"success",
		"data":null
}
~~~



### 增加

[POST] / dynamicCenter / addDynamic

参数

| 参数名称 | 是否必须 | 说明                    |
| -------- | -------- | ----------------------- |
| image    | N        | 图片URL(相对服务器链接) |
| header   | Y        | 项目时间                |
| text     | Y        | 动态内容                |
| time     | N        | 动态时间                |
| editor   | N        | 发布者                  |
| category | N        | 分类编号                |

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



## 五.留言板管理

### 一.分页查询留言板

[POST] / messageCenter/getMessage

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pagesize | Y        | 一页显示多少条 |
|          |          |                |

返回

~~~ 
{
		"code":0,
		"code":"success",
		"data":{
				"messages_list":[
					{
						"id":1,							//留言id
						"email":,						//邮箱
						"nickname":,				//留言者姓名
						"advice":,					//留言内容 建议
						"upload_time":			//上传时间
					},
				"total":25							//总条数
				]
		}
}
~~~



### 二.删除留言

[POST] / messageCenter/delMessage

参数

| 参数名称 | 是否必须 | 说明 |
| -------- | -------- | ---- |
| id       | Y        | 页数 |
|          |          |      |
|          |          |      |

返回

~~~ 
{
	"code":0,
	"msg":"success",
	"data":null
}
~~~



## 六.奖项管理

### 一.奖项查询

[POST]/awardCenter/getAllAwards

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pagesize | Y        | 一页显示多少条 |
|          |          |                |

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "awards_list": [
            {
                "id": 1,
                "name": "语文朗读比赛",
                "time": "2019/12"
            },
            {
                "id": 2,
                "name": "蓝桥杯二等奖",
                "time": "2019/02"
            },
            {
                "id": 3,
                "name": "蓝桥杯三等奖",
                "time": "2019/03"
            },
            {
                "id": 4,
                "name": "数学竞赛一等奖",
                "time": "2018/07"
            }
        ],
        "total": 4			//总数目
    }
}
~~~

### 二.奖项添加

[POST]/awardCenter/addAward

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| name     | Y        | 奖项名字 |
| time     | Y        | 获奖时间 |
|          |          |          |

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": null
}

{
    "code": 1,
    "msg": "名字为空或长度超过上限",
    "data": null
}

{
    "code": 2,
    "msg": "获奖时间不能为空",
    "data": null
}
~~~



### 三.奖项修改

[POST]/awardCenter/modifyAward

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| name     | Y        | 奖项名字 |
| time     | Y        | 获奖时间 |
| id       | Y        | 奖项id   |

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": null
}

{
    "code": 1,
    "msg": "名字为空或长度超过上限",
    "data": null
}

{
    "code": 2,
    "msg": "获奖时间不能为空",
    "data": null
}
~~~



### 四.奖项删除

[POST]/awardCenter/delAward

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| id       | Y        | 要删除的奖项id |
|          |          |                |
|          |          |                |

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": null
}
~~~



## 七.登录与注册

### 1.登录

[POST] / login

参数

| 参数名称 | 是否必须 | 说明                      |
| -------- | -------- | ------------------------- |
| username | Y        | 用户名                    |
| password | Y        | 密码                      |
| status   | Y        | 0 普通登录，1下次自动登录 |

返回

~~~
{
    "code": 1000,
    "msg": "success",
    "data": {
        "id": 30,						//登陆成功的用户id
        "name": "admin",		//用户名
        "token": 		"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbiIsImlhdCI6MTU3NjMwMjAwOCwic3ViIjoiIiwiaXNzIjoic2lwYzExNS5jb20iLCJleHAiOjE1NzY5MDY4MDh9.UhYMDQWRXQVp7nbjqg-CqYx2uwn2Za4h7GJGNJRiJSA"		//token请前端以"sipc-token"为变量名保存到Header中
    }
}

{
    "code": 1001,
    "msg": "用户不存在",
    "data": null
}

{
    "code": 1002,
    "msg": "密码错误",
    "data": null
}
~~~



### 2.注销

[POST] / logout

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": null
}
~~~



## 八.图片上传接口

[POST]/uploadHeadImage	//上传头像图片接口

参数

headImage 头像图片文件

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "image_raw": null,			//不反回头像原图
        "image_compress": "url"	//压缩图相对链接，需要保存到服务器
    }
}
~~~

[POST]/uploadProjectImage	//上传项目图片接口

参数

projectImage 项目图片文件

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "image_raw": "url",			//原图相对链接，需要保存到服务器
        "image_compress": "url"	//压缩图相对链接，需要保存到服务器
    }
}
~~~

[POST]/uploadDynamicImage	//上传动态图片接口

参数

dynamicImage 动态图片文件

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
        "image_raw": "url",			//原图相对链接，需要保存到服务器
        "image_compress": null	//不反回动态压缩图
    }
}
~~~

