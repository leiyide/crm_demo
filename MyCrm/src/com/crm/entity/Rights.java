package com.crm.entity;

import java.io.Serializable;

public class Rights implements Serializable{
	
    private Integer rid;

    private String rightname;

    private String righttype;

    private String url;

    private Integer pid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRightname() {
        return rightname;
    }

    public void setRightname(String rightname) {
        this.rightname = rightname == null ? null : rightname.trim();
    }

    public String getRighttype() {
        return righttype;
    }

    public void setRighttype(String righttype) {
        this.righttype = righttype == null ? null : righttype.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}