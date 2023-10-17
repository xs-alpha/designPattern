package com.cf.sqlTest.facade.result;


public enum ResultCodeEnum implements ResultCode {

  SUCCESS(200, "success"),
  ERROR(500, "server error"),
  BAD_REQUEST(400, "request error"),
  NOT_LOGIN(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  SIGN_ERROR(420, "signature error"),
  SIGN_TIMEOUT_ERROR(421, "signature timeout"),
  REPEAT_SUBMIT_EXPIRATION(440, "do not resubmit");


  ResultCodeEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  private int code;

  private String msg;


  @Override
  public String getMsg() {
    return this.msg;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public void setMsg(String msg) {
    this.msg = msg;
  }
}
