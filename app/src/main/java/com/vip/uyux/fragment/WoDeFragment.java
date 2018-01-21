package com.vip.uyux.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vip.uyux.R;
import com.vip.uyux.activity.FenXiangZXActivity;
import com.vip.uyux.activity.GeRenXXActivity;
import com.vip.uyux.activity.WoDeYuEActivity;
import com.vip.uyux.base.ZjbBaseFragment;
import com.vip.uyux.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View viewBar;

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
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height =ScreenUtils.getStatusBarHeight(getActivity())+(int) getActivity().getResources().getDimension(R.dimen.titleHeight);
        viewBar.setLayoutParams(layoutParams);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuE).setOnClickListener(this);
        mInflate.findViewById(R.id.imageFenXiangZX).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGeRen).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent();
        switch (view.getId()){
            case R.id.viewGeRen:
                intent.setClass(getActivity(), GeRenXXActivity.class);
                startActivity(intent);
                break;
            case R.id.imageFenXiangZX:
                intent.setClass(getActivity(), FenXiangZXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYuE:
                intent.setClass(getActivity(),WoDeYuEActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
