package com.vip.uyux.util;

import android.graphics.BitmapFactory;

/**
 * Created by zhangjiebo on 2018/2/11/011.
 *
 * @author ZhangJieBo
 */

public class ImageWidthHeight {
    public static class WidthHeight {
        private int width;
        private int heigth;

        public WidthHeight(int width, int heigth) {
            this.width = width;
            this.heigth = heigth;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeigth() {
            return heigth;
        }

        public void setHeigth(int heigth) {
            this.heigth = heigth;
        }
    }

    public static WidthHeight getImgWidthHeigth(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new WidthHeight(options.outWidth, options.outHeight);
    }
}
