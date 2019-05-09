package com.oracle.web.bean;

public class Admin {
    private Integer id;

    private String name;

    private String ausername;

    private String password;

    private String phone;

    private String touxiang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
		return "Admin [id=" + id + ", name=" + name + ", ausername=" + ausername + ", password=" + password + ", phone="
				+ phone + ", touxiang=" + touxiang + "]";
	}
    
    
}