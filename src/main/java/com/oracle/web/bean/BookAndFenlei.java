package com.oracle.web.bean;

public class BookAndFenlei extends Book{

	private BookTime bt;//用户借书情况用
	private Fenlei fenlei;
    private String htime;//该还书时间
    private Integer date;//该还书时间和现在时间差
	
	
	public BookAndFenlei() {
		super();
		// TODO Auto-generated constructor stub
	}

 

	public BookAndFenlei(BookTime bt, Fenlei fenlei) {
		super();
		this.bt = bt;
		this.fenlei = fenlei;
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

	public BookTime getBt() {
		return bt;
	}

	public void setBt(BookTime bt) {
		this.bt = bt;
	}

	 

	public String getHtime() {
		return htime;
	}



	public void setHtime(String htime) {
		this.htime = htime;
	}



	public Integer getDate() {
		return date;
	}



	public void setDate(Integer date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "BookAndFenlei [fenlei=" + fenlei + ", bt=" + bt + "]";
	}

	 
	
}
