package com.vip.uyux.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.vip.uyux.R;
import com.vip.uyux.activity.ChanPinXQCZActivity;
import com.vip.uyux.activity.WebActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.SingleBtnDialog;
import com.vip.uyux.model.CouponIndex;
import com.vip.uyux.model.GoodsShareimgs;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShareBean;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.Tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/8/27.
 */
public class MyDialog {
    public static void showReLoginDialog(final Context context) {
        final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(context, "您的账号在其他设备上登录，请重新登录", "确认");
        singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                ToLoginActivity.toLoginActivity(context);
            }
        });
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    singleBtnDialog.dismiss();
                    ToLoginActivity.toLoginActivity(context);
                }
                return false;
            }
        });
        singleBtnDialog.show();
    }

    /**
     * 单个按钮无操作
     *
     * @param msg
     */
    public static void showTipDialog(Context context, String msg) {
        final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(context, msg, "确认");
        singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                singleBtnDialog.dismiss();
            }
        });
        singleBtnDialog.show();
    }

    public static void dialogFinish(final Activity activity, String msg) {
        final SingleBtnDialog singleBtnDialog = new SingleBtnDialog(activity, msg, "确认");
        singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                singleBtnDialog.dismiss();
                activity.finish();
            }
        });
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    activity.finish();
                }
                return false;
            }
        });
        singleBtnDialog.setCancelable(false);
        singleBtnDialog.show();
    }


    public static OnChoosePicListener onChoosePicListener;

    public interface OnChoosePicListener {
        void chaKan();

        void shangChuan();
    }

    public static void setOnChoosePicListener(OnChoosePicListener onChoosePicListener) {
        MyDialog.onChoosePicListener = onChoosePicListener;
    }

    /**
     * des： 选择图片
     * author： ZhangJieBo
     * date： 2017/11/6 0006 上午 9:36
     */
    public static void choosePic(Context context) {
        View dialog_tu_pian = LayoutInflater.from(context).inflate(R.layout.dialog_tu_pian, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_tu_pian)
                .create();
        dialog_tu_pian.findViewById(R.id.textChaKan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onChoosePicListener.chaKan();
            }
        });
        dialog_tu_pian.findViewById(R.id.textShangChuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onChoosePicListener.shangChuan();
            }
        });
        dialog_tu_pian.findViewById(R.id.textQuXiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * des： 预览图片
     * author： ZhangJieBo
     * date： 2017/11/6 0006 上午 9:34
     */
    public static void showPicDialog(Context context, String img) {
        View dialog_img_show = LayoutInflater.from(context).inflate(R.layout.dialog_img_show, null);
        ImageView imageView = (ImageView) dialog_img_show.findViewById(R.id.imageView);
        GlideApp.with(context)
                .asBitmap()
                .load(img)
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_img_show)
                .create();
        alertDialog.show();
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.6); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }


    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    public static boolean checkIsSupportedWeachatPay(IWXAPI api) {
        boolean isPaySupported = api.getWXAppSupportAPI() >= com.tencent.mm.opensdk.constants.Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

    /**
     * des： 分享
     * author： ZhangJieBo
     * date： 2017/9/25 0025 上午 11:54
     */
    public static void share(final Context context, final String activity, final IWXAPI api, final String id, final ShareBean share) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_shengji = inflater.inflate(R.layout.dianlog_index_share, null);
        TextView textDes1 = (TextView) dialog_shengji.findViewById(R.id.textDes1);
        TextView textDes2 = (TextView) dialog_shengji.findViewById(R.id.textDes2);
        textDes1.setText(share.getTitle());
        SpannableString span = new SpannableString(share.getDes1() + share.getDesMoney() + share.getDes2());
        span.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.basic_color)), share.getDes1().length(), share.getDes1().length() + share.getDesMoney().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textDes2.setText(span);
        final AlertDialog alertDialog1 = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_shengji)
                .create();
        alertDialog1.show();
        dialog_shengji.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.viewWeiXin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source="web";
                wxShare(api, 0, share.getShareUrl(), share.getShareTitle(), share.getShareDes(), share.getShareImg());
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.viewPengYouQuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source="wxp";
                wxShare(api, 1, share.getShareUrl(), share.getShareTitle(), share.getShareDes(), share.getShareImg());
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.viewErWeiMa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source="";
                duoTuPengYouQuan(context, activity, id,1);
                alertDialog1.dismiss();
            }
        });
        Window dialogWindow = alertDialog1.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    private static List<File> files = new ArrayList<>();

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private static OkObject getOkObject(Context mContext, String id) {
        String url = Constant.HOST + Constant.Url.GOODS_SHAREIMGS;
        HashMap<String, String> params = new HashMap<>();
        if (((ChanPinXQCZActivity) mContext).isLogin) {
            params.put("uid", ((ChanPinXQCZActivity) mContext).userInfo.getUid());
            params.put("tokenTime", ((ChanPinXQCZActivity) mContext).tokenTime);
        }
        params.put("id", id);
        return new OkObject(params, url);
    }

    /**
     * 多图分享朋友圈
     */
    private static void duoTuPengYouQuan(final Context mContext, String activity, String id, final int flag) {
        switch (activity) {
            case "ChanPinXQCZActivity":
                ((ChanPinXQCZActivity) mContext).showLoadingDialog();
                ApiClient.post(mContext, getOkObject(mContext, id), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.LogShitou("MyDialog--onSuccess", s + "");
                        try {
                            final GoodsShareimgs goodsShareimgs = GsonUtils.parseJSON(s, GoodsShareimgs.class);
                            if (goodsShareimgs.getStatus() == 1) {
                                final List<String> goodsShareimgsImgs = goodsShareimgs.getImgs();
                                ThreadPoolHelp.Builder
                                        .cached()
                                        .builder()
                                        .execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                //这一步一定要clear,不然分享了朋友圈马上分享好友图片就会翻倍
                                                files.clear();
                                                try {
                                                    for (int i = 0; i < goodsShareimgsImgs.size(); i++) {
                                                        File file = Tools.saveImageToSdCard(mContext, goodsShareimgsImgs.get(i));
                                                        files.add(file);
                                                    }
                                                    Intent intent = new Intent();
                                                    ComponentName comp;
                                                    if (flag == 0) {
                                                        comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                                                    } else {
                                                        comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                                                        intent.putExtra("Kdescription", goodsShareimgs.getShareTitle()+"\n"+goodsShareimgs.getShareDes());
                                                    }
                                                    intent.setComponent(comp);
                                                    intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                                                    intent.setType("image/*");
                                                    ArrayList<Uri> imageUris = new ArrayList<Uri>();
                                                    for (File f : files) {
                                                        imageUris.add(Uri.fromFile(f));
                                                    }
                                                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                                                    mContext.startActivity(intent);
                                                    ((ChanPinXQCZActivity) mContext).runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            ((ChanPinXQCZActivity) mContext).cancelLoadingDialog();
                                                        }
                                                    });
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                            } else if (goodsShareimgs.getStatus() == 3) {
                                MyDialog.showReLoginDialog(mContext);
                            } else {
                                Toast.makeText(mContext, goodsShareimgs.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        ((ChanPinXQCZActivity) mContext).cancelLoadingDialog();
                        Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }

    }

    private static void wxShare(final IWXAPI api, final int flag, String url, String title, String des, final String img) {
        api.registerApp(Constant.WXAPPID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = des;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = netPicToBmp(img);
                msg.setThumbImage(bitmap);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                switch (flag) {
                    case 0:
                        req.scene = SendMessageToWX.Req.WXSceneSession;
                        break;
                    case 1:
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        break;
                    case 2:
                        req.scene = SendMessageToWX.Req.WXSceneFavorite;
                        break;
                }
                api.sendReq(req);
            }
        }).start();
    }

    public static Bitmap netPicToBmp(String src) {
        try {
            Log.d("FileUtil", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            //设置固定大小
            //需要的大小
            float newWidth = 200f;
            float newHeigth = 200f;

            //图片大小
            int width = myBitmap.getWidth();
            int height = myBitmap.getHeight();

            //缩放比例
            float scaleWidth = newWidth / width;
            float scaleHeigth = newHeigth / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeigth);

            Bitmap bitmap = Bitmap.createBitmap(myBitmap, 0, 0, width, height, matrix, true);
            return bitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static void share01(final Context context, final IWXAPI api, final String url, final String img,final String title,final  String des) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_shengji = inflater.inflate(R.layout.dianlog_share1, null);
        final AlertDialog alertDialog1 = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_shengji)
                .create();
        alertDialog1.show();
        dialog_shengji.findViewById(R.id.textViewCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source= "wxw";
                wxShare1(context, api, 0, url, img,title,des);
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source ="wxp";
                wxShare1(context, api, 1, url,img, title,des);
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.relaShouCang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wxShare1(context, api, 2, url,img, title,des);
                alertDialog1.dismiss();
            }
        });
        Window dialogWindow = alertDialog1.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    private static void wxShare1(Context context, final IWXAPI api, final int flag, String url, final String img, String title, String des) {
        api.registerApp(Constant.WXAPPID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = des;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = netPicToBmp(img);
                msg.setThumbImage(bitmap);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                switch (flag) {
                    case 0:
                        req.scene = SendMessageToWX.Req.WXSceneSession;
                        break;
                    case 1:
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        break;
                    case 2:
                        req.scene = SendMessageToWX.Req.WXSceneFavorite;
                        break;
                    default:
                        break;
                }
                api.sendReq(req);
            }
        }).start();
    }

    public static void shareYouHuiQuan(final Context context, final IWXAPI api, final CouponIndex.DataBean dataBean) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_shengji = inflater.inflate(R.layout.dianlog_you_hui_quan, null);
        TextView textMoney = dialog_shengji.findViewById(R.id.textMoney);
        TextView textLimit_money = dialog_shengji.findViewById(R.id.textLimit_money);
        TextView textName = dialog_shengji.findViewById(R.id.textName);
        TextView textSendDes = dialog_shengji.findViewById(R.id.textSendDes);
        TextView textUse_time = dialog_shengji.findViewById(R.id.textUse_time);
        TextView textShareDes = dialog_shengji.findViewById(R.id.textShareDes);
        TextView textGuiZe = dialog_shengji.findViewById(R.id.textGuiZe);
        TextView textShareTitle = dialog_shengji.findViewById(R.id.textShareTitle);
        SpannableString span = new SpannableString("¥"+dataBean.getMoney());
        span.setSpan(new RelativeSizeSpan(0.4f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textMoney.setText(span);
        textLimit_money.setText(dataBean.getLimit_money());
        textName.setText(dataBean.getName());
        textSendDes.setText(dataBean.getSendDes());
        textUse_time.setText(dataBean.getUse_time());
        textShareDes.setText(dataBean.getShare().getDes());
        textShareTitle.setText(dataBean.getShare().getTitle());
        final AlertDialog alertDialog1 = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_shengji)
                .create();
        alertDialog1.show();
        textGuiZe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, dataBean.getUrl_title());
                intent.putExtra(Constant.IntentKey.URL, dataBean.getUrl());
                context.startActivity(intent);
            }
        });
        dialog_shengji.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source= "wxw";
                wxShare1(context, api, 0, dataBean.getShare().getShareUrl(), dataBean.getShare().getShareImg(),dataBean.getShare().getShareTitle(),dataBean.getShare().getShareDes());
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                Constant.source ="wxp";
                wxShare1(context, api, 1,  dataBean.getShare().getShareUrl(), dataBean.getShare().getShareImg(),dataBean.getShare().getShareTitle(),dataBean.getShare().getShareDes());
                alertDialog1.dismiss();
            }
        });
        Window dialogWindow = alertDialog1.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }
}


