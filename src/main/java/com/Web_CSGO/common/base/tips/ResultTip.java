package com.Web_CSGO.common.base.tips;

import com.Web_CSGO.common.enums.CodeEnum;


/**
 * 返回给前台的数据封装
 *
 */
public class ResultTip<T> extends Tip {
	private T data;
	public ResultTip(Integer code, String msg, T data){
		this.code = code;
		this.message = msg;
		this.data = data;
	}
	public ResultTip(CodeEnum codeEnum){
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMsg();
	}
	public ResultTip(CodeEnum codeEnum, String msg, T data){
		this.code = codeEnum.getCode();
		this.message = msg;
		this.data = data;
	}
	public ResultTip(CodeEnum codeEnum, T resData){
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMsg();
		this.data = resData;
	}

	public T getData(){
		return data;
	}

	public void setData(T data){
		this.data = data;
	}
}
