package com.vip.uyux.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.constant.Constant;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.e("WXPayEntryActivity", "WXEntryActivity--onReq--" + req.toString());
    }


    private void sendPayResult(int errCode) {
        Log.e("WXPayEntryActivity", "WXEntryActivity--sendPayResult--" + errCode);
        Intent intent = new Intent(Constant.BroadcastCode.PAY_RECEIVER);
        intent.putExtra("error", errCode);
        sendBroadcast(intent);
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.e("WXEntryActivity", "WXEntryActivity--onResp--" + resp.errCode);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Intent intent = new Intent(Constant.BroadcastCode.WX_SHARE);
                sendBroadcast(intent);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Intent intent1 = new Intent(Constant.BroadcastCode.WX_SHARE_FAIL);
                sendBroadcast(intent1);
                finish();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                Intent intent2 = new Intent(Constant.BroadcastCode.WX_SHARE_FAIL);
                sendBroadcast(intent2);
                finish();
                break;
            default:
                Intent intent3 = new Intent(Constant.BroadcastCode.WX_SHARE_FAIL);
                sendBroadcast(intent3);
                finish();
                break;
        }
    }

//	private void sendLoginCode() {
//		Intent intent = new Intent(Constant.BROADCASTCODE.WX_LOGIN);
//		intent.putExtra("error", errCode);
//		sendBroadcast(intent);
//	}
}