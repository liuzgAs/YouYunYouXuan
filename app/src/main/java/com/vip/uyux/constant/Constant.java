package com.vip.uyux.constant;


/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "http://api.uyux.vip";
    public static String WEB_HOST = "http://api.uyux.vip";
    /**
     * 判断数据是否有改变
     */
    public static int changeControl = 2017;
    /**
     * 微信appid
     */
//    public static String WXAPPID = "wxe3ff03935adae0a6";
    public static String WXAPPID = "wx51d4b5b33bc07fa7";
    /**
     * 微信scrent
     */
    public static String WXSCRENT = "618262d37389118c85c2ff1f1f8bb783";
    /**
     * qq
     */
    public static String QQ_ID = "1106239952";
    public static String QQ_KEY = "HcA9s2rpKkLO2M5w";

    public static class Url {
        public static final String ABOUT =WEB_HOST+"/Article/info/type/about";
        /**
         * 升级
         */
        public static final String INDEX_VERSION = "/Index/version";
        /**
         * 忘记密码
         */
        public static final String LOGIN_FORGET = "/Login/forget";
        /**
         * 忘记验证码请求
         */
        public static final String LOGIN_FORGETSMS = "/Login/forgetSms";
        /**
         * 注册
         */
        public static final String LOGIN_REGISTER = "/Login/register";
        /**
         * 注册发送验证码
         */
        public static final String LOGIN_REGSMS = "/Login/regSms";
        /**
         * 用户登录
         */
        public static final String LOGIN_INDEX = "/Login/index";
        /**
         * 启动广告页
         */
        public static final String INDEX_STARTAD = "/Index/start";
        /**
         * 个人信息
         */
        public static final String USER_PROFILE = "/User/profile";
        /**
         * 单图上传
         */
        public static final String RESPOND_APPIMGADD = "/Respond/appImgAdd";
        /**
         * 修改昵称和头像
         */
        public static final String USER_SVAEINFO = "/User/svaeInfo";
        /**
         * 银行卡列表
         */
        public static final String BANK_CARDLIST = "/Bank/cardList";
        /**
         * 地址列表
         */
        public static final String USER_ADDRESS = "/User/address";
        /**
         * 地址保存
         */
        public static final String USER_SAVEADDRESS = "/User/saveAddress";
        /**
         * 删除地址
         */
        public static final String USER_DELADDRESS = "/User/delAddress";
        /**
         * 地址设为默认
         */
        public static final String USER_ADDRESSDEFAULT = "/User/addressDefault";
        /**
         * 银行卡添加前请求
         */
        public static final String BANK_CARDADDBEFORE = "/Bank/cardAddbefore";
        /**
         * 银行卡验证码
         */
        public static final String LOGIN_BINDSMS = "/Login/bindSms";
        /**
         * 银行卡添加提交
         */
        public static final String BANK_CARDADD = "/Bank/cardAdd";
        /**
         * 银行卡删除
         */
        public static final String BANK_CARDDEL = "/Bank/cardDel";
        /**
         * 分类
         */
        public static final String INDEX_CATE = "/Index/cate";
        /**
         * 首页
         */
        public static final String INDEX_HOME = "/Index/home";
        /**
         * 产品详情
         */
        public static final String GOODS_INFO = "/Goods/info";
        /**
         * 商品搜索
         */
        public static final String GOODS_SEARCH = "/Goods/search";
        /**
         * 添加到购物车
         */
        public static final String CART_ADDCART = "/Cart/addCart";
        /**
         * 购物车列表
         */
        public static final String CART_INDEX = "/Cart/index";
        /**
         * 更新购物车数量
         */
        public static final String CART_UPDATECART = "/Cart/updateCart";
        /**
         * 删除购物车
         */
        public static final String CART_DELCART = "/Cart/delCart";
        /**
         * 确认订单请求
         */
        public static final String ORDER_CONFIRMBEFORE = "/Order/confirmBefore";
        /**
         * 确认订单提交
         */
        public static final String ORDER_NEWORDER = "/Order/newOrder";
        /**
         * 支付界面
         */
        public static final String ORDER_PAY = "/Order/pay";
        /**
         * 推荐
         */
        public static final String INDEX_RECOM = "/Index/recom";
        /**
         * 商品列表
         */
        public static final String GOODS_INDEX = "/Goods/index";
        /**
         * 我的
         */
        public static final String USER_MY = "/User/my";
        /**
         * 足迹
         */
        public static final String GOODS_VIEWLOG = "/Goods/viewLog";
        /**
         * 足迹删除
         */
        public static final String GOODS_VIEWLOGDEL = "/Goods/viewLogDel";
        /**
         * 收藏商品
         */
        public static final String GOODS_COLLECT = "/Goods/collect";
        /**
         * 取消收藏
         */
        public static final String GOODS_CANCLECOLLECT = "/Goods/cancleCollect";
        /**
         * 我的收藏
         */
        public static final String USER_COLLECT = "/User/Collect";
        /**
         * 我的收藏删除
         */
        public static final String USER_COLLECTDEL = "/User/collectDel";
        /**
         * 优惠券
         */
        public static final String COUPON_INDEX = "/Coupon/index";
        /**
         * 多图分享
         */
        public static final String GOODS_SHAREIMGS = "/Goods/shareImgs";
    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String ID = "id";
        public static final String VALUE = "value";
        public static final String ORDER = "order";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String NICKNAME = "nickName";
        public static final String BEAN = "bean";
        public static final String CATE = "cate";
        public static final String PCATE = "pcate";
    }

    public static class RequestResultCode {
        public static final int XIN_YONG_KA = 2023;
        public static final int address = 2027;
        public static final int IMAGE_PICKER = 2029;
        public static final int KAI_SHI_CE_SHI = 2030;
        public static final int CITY = 2033;
    }

    public static class Acache {
        public static final String APP = "app";
        public static final String USER_INFO = "userInfo";
        public static final String TOKENTIME = "tokentime";
        public static final String LOCATION = "location";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String CITY = "city";
        public static final String CITY_ID = "city_id";
        public static final String FRIST = "frist";
        public static final String ADDRESS = "address";
        public static final String STREET = "Street";
        public static final String DISTRICT = "District";
        public static final String DID = "did";
    }

    public static class BroadcastCode {
        public static final String CHE_LIANG_BIAN_JI_DIALOG = "che_liang_bian_ji_dialog";
        public static final String PAY_RECEIVER = "pay_receiver";
        public static final String WX_LOGIN = "wxLogin";
        public static final String WX_SHARE = "wxShare";
        public static final String WX_LOGIN_FAIL = "wxLoginFail";
        public static final String WX_SHARE_FAIL = "wxShareFail";
        public static final String ZHI_FU_CG = "zhiFuCG";
        public static final String USERINFO = "userInfo";
        public static final String address = "address";
        public static final String QUAN_XUAN = "quanXuan";
        public static final String SHUA_XIN_CAR = "shuaXinCar";
        public static final String NUM_CHANGE = "num_change";
    }


}
