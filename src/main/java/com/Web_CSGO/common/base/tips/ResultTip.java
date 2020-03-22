package com.Web_CSGO.common.base.tips;

import com.Web_CSGO.common.enums.CodeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * 返回给前台的数据封装
 *
 */
public class ResultTip<T> extends Tip {

	private List<T> rows;
	private T data;

	public ResultTip( String msg){
		this.msg = msg;
	}

	public ResultTip(Integer code, String msg, T rows){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResultTip(CodeEnum codeEnum){
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
	}
	public ResultTip(CodeEnum codeEnum, String msg, T rows){
		this.code = codeEnum.getCode();
		this.msg = msg;
		this.data = rows;
	}
	public ResultTip(CodeEnum codeEnum, T resData){
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
		this.data = resData;
	}

	public ResultTip(Integer code, String msg, T data,long total ,Long pageSize){
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.total=total;
		this.pageSize=pageSize;
	}

	public ResultTip(CodeEnum codeEnum, Page page){
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
		this.rows = page.getRecords();
		this.total=page.getTotal();
		this.pageSize=page.getSize();
		this.currentPage=page.getCurrent();
	}

    public ResultTip(T list) {
        super();
    }

    public List<T> getRows(){
		return rows;
	}

	public void setRows(List<T> rows){
		this.rows = rows;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
