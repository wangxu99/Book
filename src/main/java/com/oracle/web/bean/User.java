package com.oracle.web.bean;

public class User {
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer uid;

    private String uname;

    private String username;

    private String password;

    private String phone;

    private String regtime;

    private String touxiang;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime == null ? null : regtime.trim();
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang == null ? null : touxiang.trim();
    }
    
    

	public User(Integer uid, String uname, String username, String password, String phone, String regtime,
			String touxiang) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.regtime = regtime;
		this.touxiang = touxiang;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", username=" + username + ", password=" + password
				+ ", phone=" + phone + ", regtime=" + regtime + ", touxiang=" + touxiang + "]";
	}
    
    
}