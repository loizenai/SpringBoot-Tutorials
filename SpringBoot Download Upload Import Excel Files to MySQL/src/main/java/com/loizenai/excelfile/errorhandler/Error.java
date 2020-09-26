package com.loizenai.excelfile.errorhandler;

public class Error {
	private String errCode;
	private String errDesc;
	
	public Error(String errCode, String errDesc) {
		this.errCode = errCode;
		this.errDesc = errDesc;
	}
	
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	public String getErrCode() {
		return this.errCode;
	}
	
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
	public String getErrDesc() {
		return this.errDesc;
	}
}
