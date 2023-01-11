package com.tencent.mall.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 *
 * @author Jarry
 */
public class StringUtil {

    /**
     * 判断是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return (str != null) && !"".equals(str.trim());
    }

    /**
     * 格式化模糊查询
     *
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 过滤掉集合里的空格
     *
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String l : list) {
            if (isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * 去除html标签
     */
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("<.*?>", "");
        // 去掉空格
        content = content.replaceAll(" ", "");
        return content;
    }

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String genSixRandomNum() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 生成由[A-Z,0-9]生成的随机字符串
     *
     * @param length 欲生成的字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(2);
            long result = 0;

            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    sb.append(new Random().nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }


}
