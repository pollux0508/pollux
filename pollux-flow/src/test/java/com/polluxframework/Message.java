package com.polluxframework;

/**
 * @author zhumin0508
 * created in  2018/11/5 16:14
 * modified By:
 */
public class Message {
	public static final int HELLO = 0;
	public static final int GOODBYE = 1;

	private String message;

	private int status;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Message{");
		sb.append("message='").append(message).append('\'');
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}
}
