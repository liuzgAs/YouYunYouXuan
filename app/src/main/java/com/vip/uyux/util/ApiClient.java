package com.vip.uyux.util;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.vip.uyux.model.OkObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/8/27.
 */
public class ApiClient {


    public interface CallBack {
        void onSuccess(String s);

        void onError();
    }

    public interface UpLoadCallBack {
        void onSuccess(String s);

        void onError();

        void uploadProgress(float progress);
    }

    public static void post(Context context, OkObject okObject, final CallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        HashMap<String, String> params = okObject.getParams();
        /*买家1卖家2拍摄3小程序4*/
        params.put("loginType",""+1);
        params.put("platform","android");
        okObject.setParams(params);
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());

        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .upJson(okObject.getJson())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorcode", ""+response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", ""+response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", ""+response.getException().toString());
                    }
                });
    }

    public static void get(Context context, OkObject okObject, final CallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        OkGo.<String>get(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }
                });

    }

    public static void postJson(Context context, String url, String json, final CallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        LogUtil.LogShitou("ApiClient--发送", "" + json);
        OkGo.<String>post(url)
                .tag(context)
                .headers(httpHeaders)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }
                });
    }

    /**
     * des： 上传文件
     * author： ZhangJieBo
     * date： 2017/11/8 0008 上午 11:40
     */
    public static void upFiles(Context context, OkObject okObject, List<File> files, final UpLoadCallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        HashMap<String, String> params = okObject.getParams();
        /*买家1卖家2拍摄3小程序4*/
        params.put("loginType",""+1);
        params.put("platform","android");
        okObject.setParams(params);
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .addFileParams("upload", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        callBack.uploadProgress(progress.fraction*100);
                    }
                });
    }/**
     * des： 上传文件
     * author： ZhangJieBo
     * date： 2017/11/8 0008 上午 11:40
     */
    public static void upFile(Context context, OkObject okObject, File files, final UpLoadCallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        HashMap<String, String> params = okObject.getParams();
        /*买家1卖家2拍摄3小程序4*/
        params.put("loginType",""+1);
        params.put("platform","android");
        okObject.setParams(params);
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        LogUtil.LogShitou("ApiClient--upFile", ""+files.getPath());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .params("upload", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", ""+response.body());
                        LogUtil.LogShitou("ApiClient--onErrorcode", ""+response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", ""+response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", ""+response.getException().toString());
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        callBack.uploadProgress(progress.fraction*100);
                    }
                });
    }

    public static void cancleAll(){
        OkGo.getInstance().cancelAll();
    }

}
