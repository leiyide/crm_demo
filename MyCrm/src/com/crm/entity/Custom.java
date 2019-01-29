package com.crm.entity;

import java.io.Serializable;
import java.util.Date;

public class Custom implements Serializable{

	private Integer id;
	private String name;
	private String education;
	private String phoneno;
	private Integer qq;
	private String email;
	private String customstatu;
	private Date createdate;
	private String invitename;

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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education == null ? null : education.trim();
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno == null ? null : phoneno.trim();
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getCustomstatu() {
		return customstatu;
	}

	public void setCustomstatu(String customstatu) {
		this.customstatu = customstatu == null ? null : customstatu.trim();
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getInvitename() {
		return invitename;
	}

	public void setInvitename(String invitename) {
		this.invitename = invitename == null ? null : invitename.trim();
	}
}