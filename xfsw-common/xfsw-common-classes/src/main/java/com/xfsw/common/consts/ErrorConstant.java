package com.xfsw.common.consts;

/**
 * 通信错误码常量类
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class ErrorConstant {

	// ======================================Dubbo异常===================================
	/** Dubbo未知异常 */
	public static final int DUBBO_UNKNOWN_EXCEPTION = 0;
	/** Dubbo网络异常 */
	public static final int DUBBO_NETWORK_EXCEPTION = 1;
	/** Dubbo调用超时 */
	public static final int DUBBO_TIMEOUT_EXCEPTION = 2;
	/** Dubbo业务异常 */
	public static final int DUBBO_BIZ_EXCEPTION = 3;
	/** Dubbo无服务或者禁止调用 */
	public static final int DUBBO_FORBIDDEN_EXCEPTION = 4;
	/** Dubbo序列化异常 */
	public static final int DUBBO_SERIALIZATION_EXCEPTION = 5;
	
	// ======================================系统异常-错误代码===================================
	/** 系统业务异常，此时msg会携带具体错误业务逻辑信息 */
	public final static int ERROR_BUSINESS_KNOWN = 101;
	/** 系统业务异常，此时msg不会携带具体错误业务逻辑信息 */
	public final static int ERROR_BUSINESS_UNKNOWN = 102;
	/**系统异常，msg如果携带信息，则为代码中手动抛出的可预见性异常信息*/
	public final static int ERROR_SYSTEM_KNOWN = 103;//系统服务异常，请稍候重试，如需帮助，请打开公众号【学术葩教育资源平台】在线联系客服或者拨打客服电话
	
	// ======================================登录业务-错误代码（用于具体的业务逻辑处理）======================================
	/** 平台用户未登录-1001 */
	public final static int ACCOUNT_SESSION_NOT_EXIST = 1001;//登录超时
	/** 账号尚未绑定手机号-1002 */
	public final static int ACCOUNT_NOT_BIND = 1002;
	/** 账号密码错误-1003 */
	public final static int ACCOUNT_PWD_ERROR = 1003;
	/** 账号尚未绑定手机号-1004 */
	public final static int ACCOUNT_FORBIDDEN = 1004;
	/** 微信登录失败 */
	public final static int ACCOUNT_WX_LOGIN_FAIL = 1011;
	

	// ======================================短信验证码======================================
	/** 一个业务、一个发送媒介、一个账号、一个IP60秒之内 超出发送次数---60秒之内不允许重复发送 */
	public final static int MESSAGE_CODE_60_OUT = 1101;
	/** 一个账号、一个IP地址、每个发送媒介，每天限制的 超出发送次数 */
	public final static int MESSAGE_CODE_TODAY_OUT = 1102;
	/** 短信发送失败 */
	public final static int MESSAGE_SEND_FAILED = 1103;
	/** 短信验证码校验失败 */
	public final static int MESSAGE_VERIFY_ERROR = 1104;
	/** 短信验证码业务不存在 */
	public final static int MESSAGE_CODE_BUSINESS_TYPE_NOT_EXSIT = 1105;
	
	// ======================================购物车错误代码======================================
	/** 不允许重复添加购买 */
	public final static int SHOPPING_CAR_NOT_ALLOW_REPEAT_BUY = 1201;
}
