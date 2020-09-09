# Fragrant Bot

这个项目用于发送企业微信群机器人消息。在项目中可能需要发送实时消息通知开发者， 这个工具便于开发者使用，避免重复编写相关代码。

## 作者
开发团队： 芳香小队

小队成员： 黄辉旺， 宫新程， 何毅， 楚森 

## 功能
参照企业微信[开发文档](https://work.weixin.qq.com/api/doc/90000/90136/91770 "开发文档")进行开发
发送企业微信群消息
消息类型：text， markdown, file, image
> 考虑到适用性，目前实现text, markdown消息的发送，更多功能敬请期待

## 涉及技术
spring-boot-starter
## 安装介绍
1. 从git上clone项目之后，引入依赖之后直接运行maven命令install，自动打包到本地maven库中

2. 另起web项目，将以下依赖导入到web项目中：

   
        <dependency>
            <groupId>com.ruigu.fragrant</groupId>
            <artifactId>fragrant-bot</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
3. 开启本地调试--。
## 使用介绍

**使用默认机器人：** 已经注入TextBot，MarkDownBot至容器，直接使用即可
	
	@Resource
    private TextBot textBot;
	
	textBot.loadMessage(null, null,"test，status：{}", "success").sendMessage();

**自己创建机器人：**
	
	BeanFactory beanFactory = new BeanFactory();
	TextBot textBot = (TextBot) beanFactory.getBot(BotTypeEnum.TEXT.getTypeName(),MsgFormatType.CUSTOM_TEMPLATE);
	textBot.loadMessage(null, null,"test，status：{}", "success").sendMessage();


<font color='red'> **注** </font>：配置文件中需配置urlkey，否则bean注册失败
	
	fragrant:
		bot:
			key: 8528b1a8-0115-41d2-84a9-cb39d128bff8

## 类介绍
BeanFactory ： 创建不同类型机器人
	
	getBot(String botType, int msgFormat) // param1： 消息机器人， param2: 是否用拼接字符串
TextBot: 
	
	loadMessage(List<String> mentionedList, List<String> mentionedMobileList, Object... args)  // param1: 成员id列表 param2: 成员手机列表 param3: 消息内容
MarkdownBot: 
	
	loadMessage(Object... args) // 消息内容
Bot: 
	
	sendMessage() // 发送消息

BotTypeEnum: 四种消息类型， 创建机器人时作为第一个参数

MsgFormatType： 消息是否采用模板， 创建机器人时第二个参数

BotMessage： 用于封装消息的实体类

BotConstant：用于存放消息模板及成员信息等




