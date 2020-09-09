package com.ruigu.fragrant.message;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HuangHuiWang
 * @date 2020/7/27 15:30
 */
@Data
public class BotMessage {

    public final static String MSG_TYPE_TEXT = "text";
    public final static String MSG_TYPE_MARKDOWN = "markdown";
    public final static String MSG_TYPE_IMAGE = "image";
    public final static String MSG_TYPE_FILE = "file";

    /**
     * 消息类型枚举
     */
    private String msgtype;
    /**
     * type=text时需要去构建的实体
     */
    private TextType text;
    /**
     * type=markdown时需要去构建的实体
     */
    private MarkdownType markdown;
    /**
     * type=markdown时需要去构建的实体
     */
    private FileType file;

    /**
     * 构建一个Text类型消息实体Builder
     *
     * @param content 消息内容
     * @return 返回builder
     */
    public static TextBuilder textBuilder(String content) {
        return new TextBuilder(content);
    }

    /**
     * 构建一个Markdown类型消息实体
     *
     * @param content 消息内容（Markdown格式）
     * @return 返回builder
     */
    public static MarkdownBuilder markdownBuilder(String content) {
        return new MarkdownBuilder(content);
    }

    /**
     * 构建一个File类型消息实体
     *
     * @param mediaId 消息内容（File格式）
     * @return 返回builder
     */
    public static FileBuilder fileBuilder(String mediaId) {
        return new FileBuilder(mediaId);
    }


    private BotMessage(MarkdownType markdown) {
        this.msgtype = MSG_TYPE_MARKDOWN;
        this.markdown = markdown;
    }

    private BotMessage(TextType text) {
        this.msgtype = MSG_TYPE_TEXT;
        this.text = text;
    }

    private BotMessage(FileType file) {
        this.msgtype = MSG_TYPE_FILE;
        this.file = file;
    }

    //region 消息实体类

    @AllArgsConstructor
    @Data
    private static class TextType {
        /**
         * 文本内容，最长不超过2048个字节，必须是utf8编码
         */
        private String content;
        /**
         * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
         */
        @JsonProperty("mentioned_list")
        private List<String> mentionedList;
        /**
         * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
         */
        @JsonProperty("mentioned_mobile_list")
        private List<String> mentionedMobileList;
    }

    @AllArgsConstructor
    @Data
    private static class FileType {
        /**
         * 文本内容，最长不超过2048个字节，必须是utf8编码
         */
        @JsonProperty("media_id")
        private String mediaId;

    }


    @AllArgsConstructor
    @Data
    private static class MarkdownType {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String content;
    }

    /**
     * Text类型消息Builder
     */
    public static class TextBuilder {
        /**
         * 当需要@all时候需要填入mentioned_list或mentioned_mobile_list中的
         */
        private static final String AT_ALL = "@all";
        private String content;
        private List<String> mentionedList;
        private List<String> mentionedMobileList;


        /**
         * 构造方法，消息体必填
         *
         * @param content 消息体
         */
        private TextBuilder(String content) {
            this.content = content;
        }

        /**
         * 添加userId，用于在消息中@某人
         *
         * @param mentioned 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addUserIdForAt(String... mentioned) {
            if (mentioned != null && mentioned.length > 0) {
                if (mentionedList == null) {
                    mentionedList = new ArrayList<>();
                }
                mentionedList.addAll(Arrays.asList(mentioned));
            }
            return this;
        }

        /**
         * 添加手机号，用于添加某人
         * 当无法获取到userId的时候，则可以添加手机号（需要是企业微信绑定的）
         *
         * @param mobiles 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addMobileForAt(String... mobiles) {
            if (mobiles != null && mobiles.length > 0) {
                if (mentionedMobileList == null) {
                    mentionedMobileList = new ArrayList<>();
                }
                mentionedMobileList.addAll(Arrays.asList(mobiles));
            }
            return this;
        }

        public TextBuilder atAll() {
            addUserIdForAt(AT_ALL);
            return this;
        }

        public BotMessage build() {
            return new BotMessage(new TextType(content, mentionedList, mentionedMobileList));
        }
    }

    /**
     * Markdown类型消息Builder
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MarkdownBuilder {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String content;

        public BotMessage build() {
            return new BotMessage(new MarkdownType(content));
        }
    }

    /**
     * File类型消息Builder
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FileBuilder {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String mediaId;

        public BotMessage build() {
            return new BotMessage(new FileType(mediaId));
        }
    }

}
