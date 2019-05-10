package com.oracle.web.bean;

public class Fenlei {
    private Integer fid;

    private String fname;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

	@Override
	public String toString() {
		return "Fenlei [fid=" + fid + ", fname=" + fname + "]";
	}
    
}