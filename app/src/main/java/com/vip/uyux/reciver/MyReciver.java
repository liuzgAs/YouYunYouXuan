package com.vip.uyux.reciver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.vip.uyux.activity.ReciverActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.AdvsBean;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;

import java.util.Map;

/**
 * Created by zhangjiebo on 2017/9/16 0016.
 */
public class MyReciver extends MessageReceiver {
    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        AdvsBean extraMapBean = GsonUtils.parseJSON(extraMap, AdvsBean.class);
        extraMapBean.setTitle(title);
        extraMapBean.setSummary(summary);
        Intent intent = new Intent();
        intent.setAction(Constant.BroadcastCode.ADV);
        intent.putExtra(Constant.IntentKey.BEAN, extraMapBean);
        context.sendBroadcast(intent);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotification(Context context, String s, String s1, Map<String, String> map) {
        super.onNotification(context, s, s1, map);
        LogUtil.LogShitou("MyReciver--onNotification", "s" + s);
        LogUtil.LogShitou("MyReciver--onNotification", "s1" + s1);
        String img = "";
        String code = "";
        String item_id = "0";
        String url = "";
        try {
            img = map.get("img");
            code = map.get("code");
            item_id = map.get("item_id");
            url = map.get("url");
        } catch (Exception e) {
        }
        if (item_id == null) {
            item_id = "0";
        }
        if (code == null) {
            code = "";
        }
        AdvsBean extraMapBean = new AdvsBean(img, code, Integer.parseInt(item_id), url, s, s1);
        Intent intent = new Intent();
        intent.setClass(context, ReciverActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.IntentKey.BEAN, extraMapBean);
        context.startActivity(intent);
    }
}
