package com.vip.uyux.util;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * des： 文本长度控制器
 * author： ZhangJieBo
 * date： 2017/7/7 0007 上午 9:35
 */
public class NameLengthFilter implements InputFilter {
    int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母    
    String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字

    public NameLengthFilter(int mAX_EN) {
        super();
        MAX_EN = mAX_EN;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + getChineseCount(dest.toString());
        int sourceCount = source.toString().length()
                + getChineseCount(source.toString());
        String name = "";
        int count = 0;
        int i = 0;
        if (destCount + sourceCount > MAX_EN) {
            if (destCount < MAX_EN) {
                while (!(destCount + count >= MAX_EN)) {
                    ++i;
                    name = source.subSequence(0, i).toString();
                    count = name.toString().length()
                            + getChineseCount(name.toString());
                    if (destCount + count > MAX_EN) {
                        --i;
                    }
                }
                return i == 0 ? "" : source.subSequence(0, i).toString();
            }
            return "";
        } else {
            return source;
        }
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            //楼下的朋友提供更简洁的代谢
            count++;
        }
        return count;
    }
}