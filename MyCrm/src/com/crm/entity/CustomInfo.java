package com.crm.entity;

import java.util.Date;

public class CustomInfo {
    private Integer id;

    private Integer customid;

    private Integer followmanid;

    private String statu;

    private Date startdate;

    private String lastfollowdate;

    private Date plandate;

    private String mark;

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

    public Integer getFollowmanid() {
        return followmanid;
    }

    public void setFollowmanid(Integer followmanid) {
        this.followmanid = followmanid;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu == null ? null : statu.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getLastfollowdate() {
        return lastfollowdate;
    }

    public void setLastfollowdate(String lastfollowdate) {
        this.lastfollowdate = lastfollowdate == null ? null : lastfollowdate.trim();
    }

    public Date getPlandate() {
        return plandate;
    }

    public void setPlandate(Date plandate) {
        this.plandate = plandate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}