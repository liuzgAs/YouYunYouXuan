package com.vip.uyux.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vip.uyux.R;
import com.vip.uyux.adapter.TagAdapter02;
import com.vip.uyux.base.MyDialog;
import com.vip.uyux.base.ZjbBaseActivity;
import com.vip.uyux.constant.Constant;
import com.vip.uyux.customview.FlowTagLayout;
import com.vip.uyux.customview.OnTagSelectListener;
import com.vip.uyux.model.CustomerIntegragoodsinfo;
import com.vip.uyux.model.OkObject;
import com.vip.uyux.model.ShouCangShanChu;
import com.vip.uyux.model.SimpleInfo;
import com.vip.uyux.util.ApiClient;
import com.vip.uyux.util.DpUtils;
import com.vip.uyux.util.GlideApp;
import com.vip.uyux.util.GsonUtils;
import com.vip.uyux.util.LogUtil;
import com.vip.uyux.util.ScreenUtils;
import com.vip.uyux.viewholder.ChanPinFootViewHolder;
import com.vip.uyux.viewholder.LocalImageChanPinHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChanPinJFXQActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CustomerIntegragoodsinfo.DataBean.ImgsBean> adapter;
    private List<String> goodsInfoBanner;
    private int id;
    private CustomerIntegragoodsinfo.DataBean goodsInfoData;
    private List<CustomerIntegragoodsinfo.DataBean.ImgsBean> imgs;
    private List<CustomerIntegragoodsinfo.DataBean.ImgsBean> imgs2;
    private List<CustomerIntegragoodsinfo.SkuCateBean> skuCate;
    private AlertDialog alertDialogGouWu;
    private TagAdapter02 tagAdapter;
    private List<CustomerIntegragoodsinfo.SkuLvBean> skuLv;
    private List<List<CustomerIntegragoodsinfo.SkuCateBean>> catelist = new ArrayList<>();
    private MyGuiGeAdapter myGuiGeAdapter;
    private TextView textDialogPrice;
    private TextView textGuiGe;
    private int num = 1;
    private int sku_id;
    private int buy_now = 0;
    private ImageView imageFenXiang;
    private ImageView imageShouCang;
    private View viewDiBu;
    private CustomerIntegragoodsinfo goodsInfo;
    private IWXAPI api;
    private TextView textStock_numD;
    private int stock_num;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case Constant.BroadcastCode.SHUA_XIN_U_BI:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chan_pin_xq_jf);
        api = WXAPIFactory.createWXAPI(ChanPinJFXQActivity.this, Constant.WXAPPID, true);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        imageFenXiang = (ImageView) findViewById(R.id.imageFenXiang);
        imageShouCang = (ImageView) findViewById(R.id.imageShouCang);
        viewDiBu = findViewById(R.id.viewDiBu);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("产品详情");
        viewDiBu.setVisibility(View.GONE);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CustomerIntegragoodsinfo.DataBean.ImgsBean>(ChanPinJFXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image;
                return new ChanPinFootViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TabLayout tablayout;
            private TextView textYongYouJF;
            private TextView textHas_des;
            private TextView textOldPrice;
            private TextView textTitle;
            private TextView textSaleNumStockNum;
            private TextView textDes;
            private TextView textYuanJia;
            private ConvenientBanner banner;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ChanPinJFXQActivity.this).inflate(R.layout.header_chenpin_jf, null);
                banner = (ConvenientBanner) view.findViewById(R.id.banner);
                int screenWidth = ScreenUtils.getScreenWidth(ChanPinJFXQActivity.this);
                ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenWidth;
                banner.setLayoutParams(layoutParams);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                textDes = view.findViewById(R.id.textDes);
                textYuanJia = view.findViewById(R.id.textYuanJia);
                textSaleNumStockNum = view.findViewById(R.id.textSaleNumStockNum);
                textTitle = view.findViewById(R.id.textTitle);
                textOldPrice = view.findViewById(R.id.textOldPrice);
                textHas_des = view.findViewById(R.id.textHas_des);
                textYongYouJF = view.findViewById(R.id.textYongYouJF);

                tablayout = (TabLayout) view.findViewById(R.id.tablayout);
                for (int i = 0; i < 2; i++) {
                    View item_tablayout = LayoutInflater.from(ChanPinJFXQActivity.this).inflate(R.layout.item_tablayout, null);
                    TextView textTitle = (TextView) item_tablayout.findViewById(R.id.textTitle);
                    if (i == 0) {
                        textTitle.setText("宝贝详情");
                        tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), true);
                    } else {
                        textTitle.setText("规格参数");
                        tablayout.addTab(tablayout.newTab().setCustomView(item_tablayout), false);
                    }
                }
                tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        if (position == 0) {
                            if (imgs != null) {
                                adapter.clear();
                                adapter.addAll(imgs);
                            }
                        } else {
                            if (imgs2 != null) {
                                adapter.clear();
                                adapter.addAll(imgs2);
                            }
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (goodsInfoBanner != null) {
                    banner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new LocalImageChanPinHolderView();
                        }
                    }, goodsInfoBanner);
                    banner.setPageIndicator(new int[]{R.drawable.shape_indicator_normal, R.drawable.shape_indicator_selected});
                }
                if (goodsInfoData != null) {
                    textDes.setText(goodsInfoData.getDes());
                    textYuanJia.setText(String.valueOf(goodsInfoData.getIntegra_price()));
                    textSaleNumStockNum.setText("已售" + goodsInfoData.getSaleNum() + "|剩" + goodsInfoData.getStockNum() + "件");
                    textTitle.setText(goodsInfoData.getTitle());
                    textOldPrice.setText("天猫价¥" + goodsInfoData.getOldPrice());
                    textHas_des.setText(goodsInfoData.getHas_des());
                    textYongYouJF.setText(String.valueOf(goodsInfoData.getHas_integra()));
                }
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textJiaRuGWC).setOnClickListener(this);
        findViewById(R.id.imageFenXiang).setOnClickListener(this);
        imageShouCang.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageFenXiang:
                MyDialog.share(this,"ChanPinXQActivity",api,String.valueOf(id),goodsInfo.getData().getShare());
                break;
            case R.id.imageShouCang:
                if (goodsInfo.getIsc() == 0) {
                    shouCang();
                } else {
                    quXiaoSC();
                }
                break;
            case R.id.textJiaRuGWC:
                mai();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 取消收藏
     */
    private void quXiaoSC() {
        String url = Constant.HOST + Constant.Url.GOODS_CANCLECOLLECT;
        ShouCangShanChu shouCangShanChu = new ShouCangShanChu(1, "android", userInfo.getUid(), tokenTime, id, 1);
        showLoadingDialog();
        ApiClient.postJson(ChanPinJFXQActivity.this, url, GsonUtils.parseObject(shouCangShanChu), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Toast.makeText(ChanPinJFXQActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        goodsInfo.setIsc(0);
                        imageShouCang.setImageResource(R.mipmap.shoucang_xq);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinJFXQActivity.this);
                    } else {
                        Toast.makeText(ChanPinJFXQActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinJFXQActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinJFXQActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSCOkObject() {
        String url = Constant.HOST + Constant.Url.GOODS_COLLECT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("item_id", String.valueOf(id));
        params.put("type", String.valueOf(1));
        return new OkObject(params, url);
    }

    /**
     * 收藏
     */
    private void shouCang() {
        showLoadingDialog();
        ApiClient.post(ChanPinJFXQActivity.this, getSCOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChanPinXQActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(ChanPinJFXQActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    goodsInfo.setIsc(1);
                    imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
                    if (simpleInfo.getStatus() == 1) {
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinJFXQActivity.this);
                    } else {
                        Toast.makeText(ChanPinJFXQActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChanPinJFXQActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChanPinJFXQActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CUSTOMER_INTEGRAGOODSINFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", String.valueOf(id));
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("积分产品详情", s);
                try {
                    goodsInfo = GsonUtils.parseJSON(s, CustomerIntegragoodsinfo.class);
                    if (goodsInfo.getStatus() == 1) {
                        goodsInfoBanner = goodsInfo.getBanner();
                        goodsInfoData = goodsInfo.getData();
                        imgs = goodsInfoData.getImgs();
                        imgs2 = goodsInfoData.getImgs2();
                        skuCate = goodsInfo.getSkuCate();
                        skuLv = goodsInfo.getSkuLv();
                        stock_num = goodsInfo.getData().getStockNum();
                        listList.clear();
                        List<List<CustomerIntegragoodsinfo.SkuCateBean>> listList = readTree(skuCate);
                        catelist.clear();
                        catelist.addAll(listList);
                        LogUtil.LogShitou("ChanPinXQActivity--onSuccess", "" + GsonUtils.parseObject(catelist));
                        viewDiBu.setVisibility(View.VISIBLE);
                        if (goodsInfo.getIsc() == 1) {
                            imageShouCang.setImageResource(R.mipmap.shoucang_xq_true);
                        } else {
                            imageShouCang.setImageResource(R.mipmap.shoucang_xq);
                        }

                        if (imgs != null) {
                            adapter.clear();
                            adapter.addAll(imgs);
                        }
                    } else if (goodsInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChanPinJFXQActivity.this);
                    } else {
                        showError(goodsInfo.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                try {
                    View viewLoader = LayoutInflater.from(ChanPinJFXQActivity.this).inflate(R.layout.view_loaderror, null);
                    TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                    textMsg.setText(msg);
                    viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recyclerView.showProgress();
                            initData();
                        }
                    });
                    recyclerView.setErrorView(viewLoader);
                    recyclerView.showError();
                } catch (Exception e) {
                }
            }
        });

    }


    /**
     * des： 加入购物车弹窗
     * author： ZhangJieBo
     * date： 2017/9/26 0026 下午 4:04
     */

    private void mai() {
        LayoutInflater inflater = LayoutInflater.from(ChanPinJFXQActivity.this);
        View dialog_chan_pin = inflater.inflate(R.layout.dialog_chan_pin, null);
        alertDialogGouWu = new AlertDialog.Builder(ChanPinJFXQActivity.this, R.style.dialog)
                .setView(dialog_chan_pin)
                .create();
        alertDialogGouWu.show();
        ListView listView = dialog_chan_pin.findViewById(R.id.listView);
        myGuiGeAdapter = new MyGuiGeAdapter();
        listView.setAdapter(myGuiGeAdapter);
        dialog_chan_pin.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogGouWu.dismiss();
            }
        });
        ImageView imageImg = dialog_chan_pin.findViewById(R.id.imageImg);
        GlideApp.with(ChanPinJFXQActivity.this)
                .asBitmap()
                .centerCrop()
                .transform(new RoundedCorners((int) DpUtils.convertDpToPixel(10, ChanPinJFXQActivity.this)))
                .load(goodsInfoData.getThumb())
                .into(imageImg);
        textDialogPrice = dialog_chan_pin.findViewById(R.id.textDialogPrice);
        textDialogPrice.setText( goodsInfoData.getStockNum()+"U币");
        textGuiGe = dialog_chan_pin.findViewById(R.id.textGuiGe);
        textStock_numD = dialog_chan_pin.findViewById(R.id.textStock_numD);
        textStock_numD.setText("库存" + stock_num + "件");
        ImageView imageAdd = dialog_chan_pin.findViewById(R.id.imageAdd);
        final ImageView imageDelete = dialog_chan_pin.findViewById(R.id.imageDelete);
        final EditText editNum = dialog_chan_pin.findViewById(R.id.editNum);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {
                    editNum.setText("1");
                    editNum.setSelection(1);
                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    if (goodsNum < stock_num) {
                        editNum.setText((goodsNum + 1) + "");
                        editNum.setSelection(((goodsNum + 1) + "").length());
                    } else {
                        Toast.makeText(ChanPinJFXQActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editNum.getText().toString().trim())) {

                } else {
                    int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                    if (goodsNum > 1) {
                        editNum.setText((goodsNum - 1) + "");
                        editNum.setSelection(((goodsNum - 1) + "").length());
                    }
                }
            }
        });
        editNum.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ((source.equals("0") && dest.toString().length() == 0)) {
                    return "1";
                }
                return null;
            }
        }});
        editNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    editNum.setText("1");
                    editNum.setSelection(1);
                }
//                data.setNum(Integer.parseInt(editNum.getText().toString().trim()));
//                Double price = Arith.mul((double) data.getNum(), Double.parseDouble(data.getGoods_price()));
//                ((QueRenDDActivity) getContext()).textSum.setText("¥" + price);
            }
        });
        dialog_chan_pin.findViewById(R.id.buttonSure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int goodsNum = Integer.parseInt(editNum.getText().toString().trim());
                if (goodsNum <=stock_num) {
                } else {
                    Toast.makeText(ChanPinJFXQActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    return;
                }
                alertDialogGouWu.dismiss();
                Intent intent = new Intent();
                intent.setClass(ChanPinJFXQActivity.this,QueRenDDJFActivity.class);
                intent.putExtra(Constant.IntentKey.ID,id);
                intent.putExtra(Constant.IntentKey.VALUE,editNum.getText().toString().trim());
                startActivity(intent);
            }
        });


        Window dialogWindow = alertDialogGouWu.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = ChanPinJFXQActivity.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    class MyGuiGeAdapter extends BaseAdapter {
        class ViewHolder {
            public TextView title;
            private FlowTagLayout flowTagLayout;
        }

        @Override
        public int getCount() {
            return catelist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ChanPinJFXQActivity.this).inflate(R.layout.item_guige, null);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.flowTagLayout = convertView.findViewById(R.id.flowTagLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final List<CustomerIntegragoodsinfo.SkuCateBean> skuCateBeans = catelist.get(position);
            holder.title.setText(skuLv.get(position).getName());
            holder.flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE_FALSE);
            tagAdapter = new TagAdapter02(ChanPinJFXQActivity.this);
            holder.flowTagLayout.setAdapter(tagAdapter);
            tagAdapter.clearAndAddAll(skuCateBeans);
            for (int i = 0; i < skuCateBeans.size(); i++) {
                if (skuCateBeans.get(i).isSelect()) {
                    holder.flowTagLayout.setSelect(i);
                    break;
                }
            }
            shuaXinDialog();
            holder.flowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                    Integer integer = selectedList.get(0);
                    LogUtil.LogShitou("MyGuiGeAdapter--onItemSelect", "" + integer);
                    for (int i = 0; i < skuCateBeans.size(); i++) {
                        skuCateBeans.get(i).setSelect(false);
                    }
                    skuCateBeans.get(integer).setSelect(true);
                    List<CustomerIntegragoodsinfo.SkuCateBean> cateBeanList = skuCateBeans.get(integer).getValue();
                    List<List<CustomerIntegragoodsinfo.SkuCateBean>> listList1 = new ArrayList<>();
                    for (int i = 0; i < position + 1; i++) {
                        listList1.add(catelist.get(i));
                    }
                    listList.clear();
                    List<List<CustomerIntegragoodsinfo.SkuCateBean>> listList = readTree(cateBeanList);
                    if (listList.size() > 0) {
                        listList1.addAll(listList);
                    }
                    catelist.clear();
                    catelist.addAll(listList1);
                    myGuiGeAdapter.notifyDataSetChanged();
                    shuaXinDialog();
                }


            });
            return convertView;
        }

        private void shuaXinDialog() {
            List<CustomerIntegragoodsinfo.SkuCateBean> skuCateBeans1 = catelist.get(catelist.size() - 1);
            for (int i = 0; i < skuCateBeans1.size(); i++) {
                CustomerIntegragoodsinfo.SkuCateBean skuCateBean = skuCateBeans1.get(i);
                if (skuCateBean.isSelect()) {
                    textDialogPrice.setText(skuCateBean.getPrice()+"U币");
                    sku_id = skuCateBean.getSku_id();
                }

            }
            String name = "";
            for (int i = 0; i < catelist.size(); i++) {
                for (int j = 0; j < catelist.get(i).size(); j++) {
                    if (catelist.get(i).get(j).isSelect()) {
                        name = name + catelist.get(i).get(j).getName() + " ";
                        stock_num = catelist.get(i).get(j).getStock_num();
                    }
                }
            }
            textGuiGe.setText(name);
            textStock_numD.setText("库存"+stock_num+"件");
        }
    }

    List<List<CustomerIntegragoodsinfo.SkuCateBean>> listList = new ArrayList<>();

    //递归方法
    List<List<CustomerIntegragoodsinfo.SkuCateBean>> readTree(List<CustomerIntegragoodsinfo.SkuCateBean> skuCate) {
        if (skuCate.size() > 0) {
            for (int i = 0; i < skuCate.size(); i++) {
                skuCate.get(i).setSelect(false);
            }
            skuCate.get(0).setSelect(true);
            listList.add(skuCate);
            if (skuCate.get(0).getValue().size() > 0) {
                readTree(skuCate.get(0).getValue());
            }
        }
        return listList;
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.SHUA_XIN_U_BI);
        registerReceiver(reciver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
