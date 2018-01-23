package com.vip.uyux.model;

/**
 * Created by zhangjiebo on 2017/9/28 0028.
 */
public class AliPayBean {
    /**
     * alipay_trade_app_pay_response : {"code":"10000","msg":"Success","app_id":"2017070407644871","auth_app_id":"2017070407644871","charset":"UTF-8","timestamp":"2017-09-28 09:05:49","total_amount":"0.01","trade_no":"2017092821001004160288027151","seller_id":"2088721339086625","out_trade_no":"AO201709280904554"}
     * sign : SCz6ODl/uBGlehnWc1vwQmqIlG3h0tSvPT3/ndyDml8A5Wu/OZoWnbg7vGnotccyKnWPn7ivtWPOd8Efh0A7XuNlhut9+6vc3IC7+c4BcvVJL2r3lYjouIyChEB99jkN8rp+Su51RtFhNl/BKX5wQc+KPM29BidfLU2UM0rpgan4nMdiCPhUf3HstsEVY7iWrZ0joLLH34B3cFw3aYG3o3tyDG8jhlAM4mm2j2E+JfVwQHRbxGeylUf4pCGpfxzjyMmIVHmRdMwumY3/tKwbI26UW96YPzpPEEEqoOzIGbTmdL5LGx7VDHrGYmaeNT4h4OO39Hlx5HRuzaHjb9kVEg==
     * sign_type : RSA2
     */

    private AlipayTradeAppPayResponseBean alipay_trade_app_pay_response;
    private String sign;
    private String sign_type;

    public AlipayTradeAppPayResponseBean getAlipay_trade_app_pay_response() {
        return alipay_trade_app_pay_response;
    }

    public void setAlipay_trade_app_pay_response(AlipayTradeAppPayResponseBean alipay_trade_app_pay_response) {
        this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public static class AlipayTradeAppPayResponseBean {
        /**
         * code : 10000
         * msg : Success
         * app_id : 2017070407644871
         * auth_app_id : 2017070407644871
         * charset : UTF-8
         * timestamp : 2017-09-28 09:05:49
         * total_amount : 0.01
         * trade_no : 2017092821001004160288027151
         * seller_id : 2088721339086625
         * out_trade_no : AO201709280904554
         */

        private int code;
        private String msg;
        private String app_id;
        private String auth_app_id;
        private String charset;
        private String timestamp;
        private String total_amount;
        private String trade_no;
        private String seller_id;
        private String out_trade_no;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getAuth_app_id() {
            return auth_app_id;
        }

        public void setAuth_app_id(String auth_app_id) {
            this.auth_app_id = auth_app_id;
        }

        public String getCharset() {
            return charset;
        }

        public void setCharset(String charset) {
            this.charset = charset;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }
    }
}
