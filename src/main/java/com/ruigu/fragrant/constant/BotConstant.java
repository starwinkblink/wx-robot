package com.ruigu.fragrant.constant;

/**
 * 企业微信机器人发消息对应的ID
 *
 * @author HuangHuiWang
 * @date 2020/7/24 9:39
 */
public abstract class BotConstant {

    public abstract class Text {
        public static final String TEMPLATE_TEXT = "方法:{},参数:{},执行耗时:{}";
    }

    public abstract class MarkDown {
        public static final String COMMON_INFO =
                "> 方法:<font color=\"comment\">{}</font>\n" +
                        "> 参数:<font color=\"comment\">{}</font>\n" +
                        "> 耗时:<font color=\"comment\">{}</font>ms";
        public static final String EXCEPTION_INFO =
                "<font color=\"warning\">【异常信息】</font>\n" +
                        ">来源: {}\n" +
                        ">code: <font color=\"warning\">{}</font>\n" +
                        ">详细信息:<font color=\"comment\">{}</font>";
        public static final String MQ_ERROR_INFO =
                "<font color=\"warning\">【{}】</font>消息处理失败\n" +
                        ">消息内容: {}\n" +
                        ">错误信息:<font color=\"warning\">{}</font>";
    }

    /**
     * 用户id列表
     */
    public abstract class UserId {

        public static final String HUANG_HUI_WANG = "huanghuiwang";
        public static final String GONG_XIN_CHENG = "gongxincheng";
        public static final String HE_YI = "heyi";
        public static final String ALL = "@all";

    }

    /**
     * 用户手机列表
     */
    public abstract class Mobile {

        public static final String MOBILE_HUANG_HUI_WANG = "17858903572";
        public static final String MOBILE_GONG_XIN_CHENG = "13563274342";
        public static final String MOBILE_HE_YI = "15779891341";
        public static final String ALL = "@all";
    }
}
