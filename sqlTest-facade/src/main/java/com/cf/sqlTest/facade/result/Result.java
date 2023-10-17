package com.cf.sqlTest.facade.result;


import java.io.Serializable;

/**
 * 业务结果集
 */
public final class Result<T> implements Serializable {

	private static final long serialVersionUID = 8168900316765357276L;

	private Integer code;

	private String msg;

	private T data;

	private Result() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean isSuccess() {
		return this.code == ResultCodeEnum.SUCCESS.getCode();
	}

	private static final class ResultBuilder<T> {

		private Integer code;
		private String msg;
		private T data;

		private ResultBuilder() {
		}

		private static ResultBuilder newResult() {
			return new ResultBuilder();
		}

		private ResultBuilder buildCode(Integer code) {
			this.code = code;
			return this;
		}

		private ResultBuilder buildMsg(String msg) {
			this.msg = msg;
			return this;
		}

		private ResultBuilder buildData(T data) {
			this.data = data;
			return this;
		}

		private Result build() {
			Result result = new Result();
			result.setCode(code);
			result.setMsg(msg);
			result.setData(data);
			return result;
		}
	}


	public static <T> Result<T> buildSuccessResult() {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.SUCCESS.getCode())
				.buildMsg(ResultCodeEnum.SUCCESS.getMsg())
				.build();
	}


	public static <T> Result<T> buildErrorResult() {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.ERROR.getCode())
				.buildMsg(ResultCodeEnum.ERROR.getMsg())
				.build();
	}

	public static <T> Result<T> buildResult(ResultCode resultCode) {
		return ResultBuilder.newResult()
				.buildCode(resultCode.getCode())
				.buildMsg(resultCode.getMsg())
				.build();
	}

	public static <T> Result<T> buildResult(ResultCode resultCode, T data) {
		return ResultBuilder.newResult()
				.buildCode(resultCode.getCode())
				.buildMsg(resultCode.getMsg())
				.buildData(data).build();
	}


	public static <T> Result<T> buildSuccessResult(T data) {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.SUCCESS.getCode())
				.buildMsg(ResultCodeEnum.SUCCESS.getMsg())
				.buildData(data).build();
	}


	public static <T> Result<T> buildErrorResult(T data) {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.ERROR.getCode())
				.buildMsg(ResultCodeEnum.ERROR.getMsg())
				.buildData(data).build();
	}

//  public static <T> Result<T> buildErrorResult(BusinessException ex) {
//    return ResultBuilder.newResult()
//        .buildCode(ex.getCode())
//        .buildMsg(ex.getMessage())
//        .build();
//  }

	public static <T> Result<T> buildErrorResult(String msg, T data) {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.ERROR.getCode())
				.buildMsg(msg)
				.buildData(data).build();
	}

	public static <T> Result<T> buildErrorResult(String msg) {
		return ResultBuilder.newResult()
				.buildCode(ResultCodeEnum.ERROR.getCode())
				.buildMsg(msg)
				.build();
	}

}