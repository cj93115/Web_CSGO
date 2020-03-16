package com.Web_CSGO.common.enums;
/*
 *@description:返回代码枚举类
 *@date:2018/11/15
 *@version:1.0
 *@author: hdf
 */
public enum CodeEnum {
	SENSE_SUCCESS(0,"success"),
	SUCCESS(200,"成功"),
	SAVE_SUCCESS(200,"保存成功"),
	UPDATE_SUCCESS(200,"编辑成功"),
	DELETE_SUCCESS(200,"删除成功"),
	SAVE_FAILD(-1,"保存失败"),
	UPDATE_FAILD(-2,"更新错误"),
	OPERATION_FAILD(205,"操作失败"),
	DELETE_FAILD(-3,"删除错误"),
	UNKNOWN_ERROR(-4,"未知错误"),
	PARAMS_ERROR(-5,"参数不合法!"),
	PARAMS_INCOMPLETENESS(-6,"参数不完整"),
	QUERY_RESULT_ERROR(-7,"查询结果异常"),
	NON_CONFORMITY_CONDITIONON(-8,"不符合操作条件"),
	DATABASE_OPERATION_ERROR(-9,"数据库操作失败"),
	PERMISSION_UNLOGIN(-10,"用户未登录"),
	NO_AUTHORITY (-11,"用户无权限"),
	KAPTCHA_ERROR(-12,"验证码错误"),
	SERVER_ERROR(500,"服务器异常"),
	TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),
	ACCOUNT_NOT_FOUND(403, "用户名不存在"),
	OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
	USER_ALREADY_REG(401,"该用户已经注册"),
	USER_PASSWORD_ERROR(406,"密码错误"),
	ACCOUNT_NO_ROLE(407,"账户无权限，请先设置权限"),
	USER_FROZEN(407,"该账户已被冻结，请联系管理员"),
	TASK_IS_RUNNING(-13,"任务运行中"),
	UNICODE_EXIST(-408,"编号已存在"),
	EXIST(-409,"不能重复添加"),
	MAJOR_CODE_REPEAT(-14,"新增失败,参数重复"),
	DEPT_CODE_REPEAT(-15,"操作失败,数据已存在"),
	;

	private Integer code;
	private String msg;

	private CodeEnum(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}


	public String getMsg() {
		return msg;
	}
}
