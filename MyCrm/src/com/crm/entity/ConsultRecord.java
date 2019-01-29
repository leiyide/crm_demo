package com.crm.entity;

import java.util.Date;

public class ConsultRecord {
    private Integer id;

    private Integer customid;

    private Integer consultmanid;

    private String consultstatu;

    private Date consultdate;

    private String result;
    
    private Custom custom;
    
    private String name;
    
    private String phoneno;
    
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public Integer getConsultmanid() {
        return consultmanid;
    }

    public void setConsultmanid(Integer consultmanid) {
        this.consultmanid = consultmanid;
    }

    public String getConsultstatu() {
        return consultstatu;
    }

    public void setConsultstatu(String consultstatu) {
        this.consultstatu = consultstatu == null ? null : consultstatu.trim();
    }

    public Date getConsultdate() {
        return consultdate;
    }

    public void setConsultdate(Date consultdate) {
        this.consultdate = consultdate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
    
}