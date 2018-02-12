package com.vip.uyux.constant;


/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "http://app.uyux.vip/api/";
    public static String WEB_HOST = "http://app.uyux.vip/api/";
    /**
     * 判断数据是否有改变
     */
    public static int changeControl = 2017;
    public static String source = "";
    public static boolean isLogin = false;
    /**
     * 微信appid
     */
    public static String WXAPPID = "wxe3ff03935adae0a6";
//    public static String WXAPPID = "wx51d4b5b33bc07fa7";
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
        public static final String ABOUT = WEB_HOST + "/Article/info/type/about";
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
         * 银行卡验证码
         */
        public static final String LOGIN_BANKSMS = "/Login/bankSms";
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
        /**
         * 不可提现佣金
         */
        public static final String WITHDRAW_NOTWITHDRAW = "/Withdraw/notWithdraw";
        /**
         * 可提现佣金
         */
        public static final String WITHDRAW_CWITHDRAW = "/withdraw/Cwithdraw";
        /**
         * 分销佣金
         */
        public static final String WITHDRAW_DISTRIBUTION = "/Withdraw/distribution";
        /**
         * 预计佣金
         */
        public static final String WITHDRAW_ESTIMATE = "/Withdraw/Estimate";
        /**
         * 我的客户
         */
        public static final String CUSTOMER = "/Customer";
        /**
         * 我的团队
         */
        public static final String CUSTOMER_MYTEAM = "/Customer/myTeam";
        /**
         * 分红中心
         */
        public static final String BONUS = "/Bonus";
        /**
         * 产品分红
         */
        public static final String BONUS_GETPROBONUS = "/Bonus/getProBonus";
        /**
         * 我的积分
         */
        public static final String CUSTOMER_GETINTEGRAL = "/Customer/getIntegral";
        /**
         * 积分商城
         */
        public static final String CUSTOMER_GETINTEGRALSHOP = "/Customer/getIntegralShop";
        /**
         * 积分产品详情
         */
        public static final String CUSTOMER_INTEGRAGOODSINFO = "/Customer/integraGoodsInfo";
        /**
         * 订单列表
         */
        public static final String ORDER = "/User/myOrder";
        /***
         * 确认收货
         */
        public static final String USER_CONFIRMSHIP = "/User/confirmShip";
        /**
         * 兑换记录
         */
        public static final String BONUS_EXCHANGERECODE = "/Score/logs";
        /**
         * 修改密码
         */
        public static final String USER_PWDSAVE = "/User/pwdSave";
        /**
         * 意见反馈
         */
        public static final String USER_FEEDBACK = "/User/feedback";
        /**
         * 常见问题
         */
        public static final String FAQ_INDEX = "/Faq/index";
        /**
         * 取消订单
         */
        public static final String USER_CANCELORDER = "/User/cancelOrder";
        /**
         * 验证码登录
         */
        public static final String LOGIN_SMS = "/Login/sms";
        /**
         * 分享中心
         */
        public static final String SHARE_INDEX = "/Share/index";
        /**
         * 优云商学院
         */
        public static final String FAQ_EUBUSINESS = "/Faq/EUBusiness";
        /**
         * 余额提现请求
         */
        public static final String WITHDRAW_ADDBEFORE = "/Withdraw/addBefore";
        /**
         * 佣金提现请求
         */
        public static final String WITHDRAW_ADDBEFORECOM = "/Withdraw/addBeforeCom";
        /**
         * 提现提交
         */
        public static final String WITHDRAW_ADDDONE = "/Withdraw/addDone";
        /**
         * 佣金提现提交
         */
        public static final String WITHDRAW_ADDDONECOM = "/withdraw/addDoneCom";
        /**
         * 余额提现记录
         */
        public static final String WITHDRAW_TBALANCE = "/Withdraw/tBalance";
        /**
         * 佣金提现记录
         */
        public static final String WITHDRAW_TCOMMISSION = "/Withdraw/tCommission";
        /**
         * 余额明细
         */
        public static final String WITHDRAW_BALANCE = "/Withdraw/balance";
        /**
         * 分销订单
         */
        public static final String SHARE_ORDER = "/Share/order";
        /**
         * 预计佣金
         */
        public static final String SHARE_ESTIMATE = "/Share/estimate";
        /**
         * 物流助手
         */
        public static final String MASSAGE_WULIU = "/massage/wuliu";
        /**
         * 登陆后绑定微信
         */
        public static final String LOGIN_LOGINWXBIND = "/Login/loginWxBind";
        /**
         * 微信授权后判断是否需要绑定电话
         */
        public static final String LOGIN_WXBIND = "/Login/wxBind";
        /**
         * 微信授权绑定电话提交
         */
        public static final String LOGIN_WXBINDSMS = "/Login/wxBindSms";
        /**
         * 购物车数量
         */
        public static final String CART_NUM = "/Cart/num";
        /**
         * 分享回调
         */
        public static final String SHARE_SHAREAFTER = "/Share/shareAfter";
        /**
         * 订单详情
         */
        public static final String USER_ORDERINFO = "/User/orderInfo";
        /**
         * 余额支付
         */
        public static final String PAY_BALANCEPAY = "/Respond/blance";
        /**
         * 评价列表
         */
        public static final String COMMENT = "/Comment";
        /**
         * 评价请求
         */
        public static final String COMMENT_ADDBEFORE = "/Comment/addBefore";
        /**
         * 评价提交
         */
        public static final String COMMENT_ADDSUBMIT = "/Comment/addSubmit";
        /**
         * 商品评价列表
         */
        public static final String COMMENT_GOODS = "/Comment/goods";
        /**
         * 售后
         */
        public static final String AFTERS = "/Afters";
        /**
         * 售后前请求
         */
        public static final String AFTERS_ADDBEFORE = "/Afters/addBefore";
        /**
         * 售后申请提交
         */
        public static final String AFTERS_ADDSUBMIT = "/Afters/addSubmit";
        /**
         * 售后记录
         */
        public static final String AFTERS_LOGS = "/Afters/logs";
        /**
         * 售后记录详情
         */
        public static final String AFTERS_LOGSINFO = "/Afters/logsInfo";
        /**
         * 吉芬商品提交订单
         */
        public static final String SCORE_NEWORDER = "/Score/newOrder";
        /**
         * 吉芬商品确认订单
         */
        public static final String SCORE_CONFIRMBEFORE = "/Score/confirmBefore";
        /**
         * 直推分红
         */
        public static final String BONUS_DIRECT = "/Bonus/direct";
        /**
         * 购物车推荐商品
         */
        public static final String CART_RECOM = "/Cart/recom";
        /**
         * 推荐商品前请求
         */
        public static final String BONUS_SUPERIORITYBEFORE = "/Bonus/superiorityBefore";
        /**
         * 推荐商品前提交
         */
        public static final String BONUS_SUPERIORITYSUBMIT = "/Bonus/superioritySubmit";
        /**
         * 推荐商品申请进度
         */
        public static final String BONUS_SUPERIORITYLOGS = "/Bonus/superiorityLogs";
        /**
         * 测评提交
         */
        public static final String EVALUATION_ADDSUBMIT = "/Evaluation/addSubmit";

    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String ID = "id";
        public static final String OGID = "ogId";
        public static final String VALUE = "value";
        public static final String ORDER = "order";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String NICKNAME = "nickName";
        public static final String BEAN = "bean";
        public static final String CATE = "cate";
        public static final String PCATE = "pcate";
        public static final String ISNEW = "isNew";
        public static final String ISHOT = "isHot";
        public static final String ISGRADE = "isGrade";
    }

    public static class RequestResultCode {
        public static final int XIN_YONG_KA = 2023;
        public static final int address = 2027;
        public static final int IMAGE_PICKER = 2029;
        public static final int KAI_SHI_CE_SHI = 2030;
        public static final int CITY = 2033;
        public static final int YOU_HUI_QUAN = 2034;
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
        public static final String SHUAXINDD = "shuaXinDD";
        public static final String TIXIAN = "tiXian";
        public static final String BANG_DING = "bangDing";
        public static final String ADV = "adv";
        public static final String SHUA_XIN_PING_JIA = "shua_xin_ping_jia";
        public static final String SHUA_XIN_SHOW_HOU = "shuaXinShouHou";
        public static final String SHUA_XIN_U_BI = "shuaXinUBI";
        public static final String SET_MAIN_TAB = "set_main_tab";
    }


}
