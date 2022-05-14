package com.blacktv.regex;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式 {

    /**
     * 匹配字符串中包含的emoji，并为其套上标签
     * 有极个别的emoji这里匹配不到，例如：\uE034，但是这个在微信里可以显示。。。。
     *
     * @param string
     * @return
     */
    public static String emojiHandle(String string) {
        if (StringUtils.isBlank(string)) {
            return string;
        }
        //去除所有转义符
        string = StringEscapeUtils.unescapeJava(string);

        String result = string + "";
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(string);
        int matcherStart = 0;
        Map<String, String> emojiHit = new HashMap<>();
        while (matcher.find(matcherStart)) {
            String emoji = matcher.group(0);
            if (emojiHit.get(emoji) == null) {
                result = result.replace(emoji, "[emoji=" + emoji + "]");
//                System.out.println("匹配到emoji：" + emoji);
                emojiHit.put(emoji, emoji);
            }
            matcherStart = matcher.end();
        }
        return result;
    }

    /**
     * 清理字符串
     *
     * @param content
     * @param needSpecialSymbols true为保留部分特殊字符，false为任何特殊字符都不要
     * @return
     */
    public static String clearString(String content, boolean needSpecialSymbols) {
        content = StringEscapeUtils.unescapeJava(content);
        //保留中文、数字、英文
        String regEx = "[a-zA-Z0-9-\u4e00-\u9fa5]";
        //保留中文、数字、英文、以及下列特殊字符
        String regExNeedSpecialSymbols = "[a-zA-Z0-9-\u4e00-\u9fa5]|[ _`!@#$%^&*()+=|{}’:;’, .<>/?！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern pattern = null;
        if (needSpecialSymbols) {
            pattern = Pattern.compile(regExNeedSpecialSymbols);
        } else {
            pattern = Pattern.compile(regEx);
        }
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(emojiHandle("韭香不怕巷子深\uD83C\uDF31"));
        System.out.println(clearString("韭香..不怕巷子深\uD83C\uDF31",false));
        System.out.println(clearString("韭香..不怕巷子深\uD83C\uDF31",true));
    }
}
