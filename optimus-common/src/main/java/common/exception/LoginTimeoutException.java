package common.exception;

/**
 * 登录超时异常
 */
public class LoginTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginTimeoutException() {
        super();
	}

	public LoginTimeoutException(String msg) {
		super(msg);
	}

}
