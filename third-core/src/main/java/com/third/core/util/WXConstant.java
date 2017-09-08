package com.third.core.util;

public class WXConstant {
	public static final String getAccessTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	public static final String getOAuthTokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
	public static final String getUserInfoURL = "https://api.weixin.qq.com/cgi-bin/user/info?";
	public static final String createGroupURL = "https://api.weixin.qq.com/cgi-bin/groups/create?";
	public static final String updateCustGroupURL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?";
	public static final String templateMsgURL = "https://api.weixin.qq.com/cgi-bin/message/template/send?";

	public static final String msgType_event = "event";
	public static final String msgType_text = "text";
	public static final String msgType_news = "news";
	public static final String msgType_transfer = "transfer_customer_service";

	public static final String event_subscribe = "subscribe";
	public static final String event_unsubscribe = "unsubscribe";

	public static final String WX_CUSTOMER = "wx_customer";
	public static final String WX_ERROR_MSG = "wx_error_msg";
	public static final String WX_MESSAGE = "wx_msg";
	public static final String WX_OPENID = "wx_openid";
	public static final String WX_VCODE = "vcode";
	public static final String WX_NAME = "name";
	public static final String WX_PHONE = "phone";

	public static final String WX_ERR_NOT_BIND_CUST = "此OpenId 未绑定任何顾客";
	public static final String WX_ERR_MUST_FROM_WX = "请从微信服务号中访问铂玛";
	public static final String WX_ERRO_NO_AUTH = "没有权限访问";
	public static final String WX_ERR_CANNOT_GET_OPENID = "不能获取微信openID信息，请稍后再试";
	public static final String WX_ERR_SAVE_REV_ERROR = "保存预约出错，请稍后再试";
	public static final String WX_ERR_ORDER_NOT_EXIST = "订单号不存在";
	public static final String WX_ERR_RESERVE_NOT_EXIST = "预约号不存在";
	public static final String WX_ERR_CRE_CUSTOMER = "服务器休息一会，请稍后再试";
	public static final String WX_ERR_VCODE_NOT_SAME = "您输入的验证码不正确";

	public static final String WX_SUCC_REV_SAVED = "预约成功！";
	public static final String WX_SUCC_REV_CHANGED = "预约修改成功！";
	public static final String WX_SUCC_CUSTOMER_CREATED = "恭喜您成为铂玛会员，请使用我的铂玛功能查看您的订单信息";
}
