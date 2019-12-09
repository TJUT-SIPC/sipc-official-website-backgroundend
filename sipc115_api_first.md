# 115官网API(一)

## 一.首页

[GET]

参数

~~~ 
无
~~~

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
    	"project_list":[
    		{
    			"id":1,
    			"description":"一起去玩耍",		 //项目描述
    			“image”:{
    				“compress”:,					//缩略图url
    				“raw”:,						//原图url
    			}
    		}
    	],
    	"award_list":[
    		{
    			"id":1,
    			"name":"蓝桥杯大赛一等奖",       	//获奖名称
    			"time":"2019-03-06"			    //获奖时间
    		}
    	],
    }
}
~~~



## 二.首页留言

[POST] / sendMessage

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| email    | Y        | 用户邮箱       |
| nickname | Y        | 留言者尊姓大名 |
| Advice   | Y        | 提交建议       |

返回

~~~ 
{
	"code": 0,
    "msg": "提交成功",
    "data": null
}

{
	"code":1,
	"msg":"邮箱格式错误",
	"data":null
}
~~~



## 三.动态

[GET] / dynamics

参数

| 参数名称 | 是否必须 | 说明                     |
| -------- | -------- | ------------------------ |
| page     | Y        | 页数（每页返回五条动态） |

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
    	“dynamic_list”:[					//分页，每页返回五条动态
    		{
    			"id":1,
    			"picture":,
    			"header":"今天是个好日子",	//动态标题
    			"text":"好日子的原因",		//动态内容
    			"time":“2019-12-12”,		//动态发布时间
    			"editor":"李同学",			//动态发布作者
    			"catagory":1				//动态分类（待定）
    		}
    	]
    }
}
~~~



## 四.获取寄语

[GET] / wishes

参数

~~~ 
无
~~~

返回

~~~ 
{
    "code": 0,
    "msg": "success",
    "data": {
    	“wishes_list”:[			//随机返回15条寄语
    		{
    			"id":1,
    			"name":"15届的同学",
    			"word":"祝115越来越好"
    		}
    	]
    }
}
~~~



## 五.上传寄语

[POST] / sendWishes

参数

| 参数名称 | 是否必须 | 说明         |
| -------- | -------- | ------------ |
| name     | Y        | 写寄语的人名 |
| word     | Y        | 寄语内容     |

返回

~~~ 
{
	"code": 0,
    "msg": "提交成功",
    "data": null
}
~~~

