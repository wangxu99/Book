package com.oracle.web.bean;

public class Book {
    private Integer bid;

    private Integer flid;

    private String bname;

    private String money;

    private String press;

    private String author;

    private Integer stock;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getFlid() {
        return flid;
    }

    public void setFlid(Integer flid) {
        this.flid = flid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", flid=" + flid + ", bname=" + bname + ", money=" + money + ", press=" + press
				+ ", author=" + author + ", stock=" + stock + "]";
	}
    
}