package com.thd.processmanagementserver.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * com.thd.springboot.framework.utils.PropertyUtils
 *
 * @author: wanglei62
 * @DATE: 2020/1/21 17:32
 **/
public class PropertyUtils {
    private static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     *
     * @param text
     * @return
     */
    public static String camel2Underline(String text) {
        if (StringUtils.isNotBlank(text)) {
            int len = text.length();
            StringBuffer sb = new StringBuffer(len + len >> 1);
            for (int i = 0; i < len; i++) {
                char c = text.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE).append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return text;
    }

    /**
     * 下划线转驼峰
     *
     * @param text
     * @return
     */
    public static String underline2Camel(String text) {
        if (StringUtils.isNotBlank(text)) {
            int len = text.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = text.charAt(i);
                if (c == UNDERLINE && (++i) < len) {
                    sb.append(Character.toUpperCase(text.charAt(i)));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return text;
    }
}
