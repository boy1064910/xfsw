package com.xfsw.common.classes;

/**
 * 业务异常
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5570238415961266871L;
	/**
	 * 系统业务异常，此时msg会携带具体错误业务逻辑信息，更多错误码常量可参考
	 * @see com.xfsw.common.consts.ErrorConstant
	 */
	private int code = 101;
	private String message;
	private Throwable cause;

	public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(String message) {
    	this.message = message;
    }

    public BusinessException(Throwable cause) {
        this.cause = cause;
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, Throwable cause) {
        this.code = code;
        this.cause = cause;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
