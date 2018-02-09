package com.vip.uyux.interfacepage;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/2/9/009.
 *
 * @author ZhangJieBo
 */

public interface OnPictureListener {
    void addPicture(List<LocalMedia> localMediaList,int type);
    void showPicture(List<LocalMedia> localMediaList,int position);
}
