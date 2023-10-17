package com.cf.sqlTest.facade.result;

/**
 * 返回Code接口限定
 */
public interface ResultCode {

  /**
   * getMsg
   */
  String getMsg();

  /**
   * getCode
   *
   * @return int
   */
  int getCode();

  /**
   * setMsg
   *
   * @return void
   */
  void setMsg(String msg);

}
