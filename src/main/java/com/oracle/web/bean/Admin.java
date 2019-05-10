package com.oracle.web.bean;

public class Admin {
    private Integer aid;

    private String aname;

    private String ausername;

    private String password;

    private String phone;

    private String touxiang;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getAusername() {
        return ausername;
    }

    public void setAusername(String ausername) {
        this.ausername = ausername == null ? null : ausername.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang == null ? null : touxiang.trim();
    }

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", ausername=" + ausername + ", password=" + password
				+ ", phone=" + phone + ", touxiang=" + touxiang + "]";
	}
    
}