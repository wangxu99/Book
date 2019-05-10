package com.oracle.web.bean;

public class BookAndFenlei extends Book{

	
	private Fenlei fenlei;

	public BookAndFenlei() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookAndFenlei(Fenlei fenlei) {
		super();
		this.fenlei = fenlei;
	}

	public Fenlei getFenlei() {
		return fenlei;
	}

	public void setFenlei(Fenlei fenlei) {
		this.fenlei = fenlei;
	}

	@Override
	public String toString() {
		return "BookAndFenlei [fenlei=" + fenlei + "]";
	}
	
}
