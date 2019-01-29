package com.crm.entity;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer id;

    private String username;

    private String pass;

    private String nickname;

    private String realname;

    private Integer jobinfoid;

    private Integer departmentid;

    private String phoneno;

    private String officetel;

    private String workstatu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getJobinfoid() {
        return jobinfoid;
    }

    public void setJobinfoid(Integer jobinfoid) {
        this.jobinfoid = jobinfoid;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getOfficetel() {
        return officetel;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel == null ? null : officetel.trim();
    }

    public String getWorkstatu() {
        return workstatu;
    }

    public void setWorkstatu(String workstatu) {
        this.workstatu = workstatu == null ? null : workstatu.trim();
    }
}