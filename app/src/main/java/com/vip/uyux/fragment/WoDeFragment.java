package com.vip.uyux.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.activity.FenXiangZXActivity;
import com.vip.uyux.activity.GeRenXXActivity;
import com.vip.uyux.activity.WoDeCPActivity;
import com.vip.uyux.activity.WoDeYuEActivity;
import com.vip.uyux.activity.YouHuiQuanActivity;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ToLoginActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.UserMy;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View viewBar;
    private ImageView imageHead;
    private TextView textName;

    public WoDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_wo_de, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        viewBar = mInflate.findViewById(R.id.viewBar);
        imageHead = mInflate.findViewById(R.id.imageHead);
        textName = mInflate.findViewById(R.id.textName);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(getActivity()) + (int) getActivity().getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuE).setOnClickListener(this);
        mInflate.findViewById(R.id.imageFenXiangZX).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGeRen).setOnClickListener(this);
        mInflate.findViewById(R.id.viewYouHuiQuan).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeCP).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_MY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        if (isLogin){
            showLoadingDialog();
            ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
                @Override
                public void onSuccess(String s) {
                    cancelLoadingDialog();
                    LogUtil.LogShitou("WoDeFragment--onSuccess",s+ "");
                    try {
                        UserMy userMy = GsonUtils.parseJSON(s, UserMy.class);
                        if (userMy.getStatus()==1){

                        }else if (userMy.getStatus()==3){
                            MyDialog.showReLoginDialog(getActivity());
                        }else {
                            Toast.makeText(getActivity(), userMy.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),"数据出错", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError() {
                    cancelLoadingDialog();
                    Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewWoDeCP:
                if (isLogin){
                    intent.setClass(getActivity(), WoDeCPActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewYouHuiQuan:
                if (isLogin){
                    intent.setClass(getActivity(), YouHuiQuanActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewGeRen:
                if (isLogin){
                    intent.setClass(getActivity(), GeRenXXActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.imageFenXiangZX:
                if (isLogin){
                    intent.setClass(getActivity(), FenXiangZXActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewYuE:
                if (isLogin){
                    intent.setClass(getActivity(), WoDeYuEActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            default:
                break;
        }
    }
}
